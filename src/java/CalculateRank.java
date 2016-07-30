
import entities.RegionData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import services.CareerMove;

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

    public static void main(String args[]) {
        EntityManager em = CareerMove.EM;
        Query q1 = em.createQuery("select distinct rd.regionDataPK.industry from RegionData rd");
        List<String> industries = q1.getResultList();
        for (int i = 0; i < industries.size(); i++) {
            Query q2 = em.createQuery("select distinct rd.regionDataPK.anzsco from RegionData rd where rd.regionDataPK=:industry");
            q2.setParameter(":industry", industries.get(i));
            List<String> occupations = q2.getResultList();
            for (int j = 0; j < occupations.size(); j++) {
                doIndustryOcc(industries.get(i), occupations.get(j));
            }
        }
    }

    public static void doIndustryOcc(String industry, String anzsco) {
        Query q = CareerMove.EM.createQuery("select r from RegionData r where r.regionDataPK.industry=:industry and r.regionDataPK.anzsco=:anzsco");
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
        for(int i=1;i<regionData.size();i++) {
            regionData.get(i).setRank(i);
            CareerMove.EM.getTransaction().begin();
            CareerMove.EM.merge(regionData.get(i));
            CareerMove.EM.getTransaction().commit();
        }
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
        score+=rd.getSA4UnempRateScore();
        score+=rd.getTradeMarksScore();
        score+=rd.getUnempRateScore();
        return score;
    }
}
