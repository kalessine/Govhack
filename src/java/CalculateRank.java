
import entities.RegionData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Owner
 */
public class CalculateRank {

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("GovhackPU");
    public static final EntityManager em = emf.createEntityManager();
    public static void main(String args[]) {
        Query q1 = em.createQuery("select distinct rd.regionDataPK.industry from RegionData rd");
        List<String> industries = q1.getResultList();
        for (int i = 0; i < industries.size(); i++) {
            Query q2 = em.createQuery("select distinct rd.regionDataPK.anzsco from RegionData rd where rd.regionDataPK.industry=:industry");
            q2.setParameter("industry", industries.get(i));
            List<String> occupations = q2.getResultList();
            for (int j = 0; j < occupations.size(); j++) {
                doIndustryOcc(industries.get(i), occupations.get(j));
            }
        }
    }

    public static void doIndustryOcc(String industry, String anzsco) {
        Query q = em.createQuery("select r from RegionData r where r.regionDataPK.industry=:industry and r.regionDataPK.anzsco=:anzsco");
        q.setParameter("industry", industry);
        q.setParameter("anzsco", anzsco);
        List<RegionData> regionData = q.getResultList();
        for(int i=0;i<regionData.size();i++) {
            regionData.get(i).setTotalScore(calculate(regionData.get(i)));
        }
        Comparator c = new Comparator(){
            public int compare(Object o1, Object o2) {
                RegionData r1 = (RegionData)o1;
                RegionData r2 = (RegionData)o2;
                return r1.getTotalScore()-r2.getTotalScore();
            }
        };
        Collections.sort(regionData,c);
        em.getTransaction().begin();
        for(int i=0;i<regionData.size();i++) {
            regionData.get(i).setRank(i);
            em.merge(regionData.get(i));
        }
        em.getTransaction().commit();
        em.clear();
    }
    public static int calculate(RegionData rd) {
        int score = rd.getAverageAnnualMovementScore();
        score+=rd.getHousePriceScore();
        score+=rd.getIncreaseBusScore();
        score+=rd.getJobVacancyScore();
        score+=rd.getMedianIncomeScore();
        score+=rd.getNewBusinessScore();
        score+=rd.getOccUnemp1315Score();
        score+=rd.getPatentIncreaseScore();
        score+=rd.getRentPriceScore();
        // null pointers in this row
        //score+=rd.getSA4UnempRateScore();
        score+=rd.getTradeMarksScore();
        score+=rd.getUnempRateScore();
        //score+=rd.getNumBus1315Score();
        return score;
    }
}
