
import entities.RegionData;
import entities.RegionDataPK;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.persistence.EntityManager;
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
public class Load {
    public static void main(String args[]) throws FileNotFoundException, IOException {
         EntityManager em = CareerMove.EMF.createEntityManager();
         String industry = "8";
         FileInputStream fis = new FileInputStream("8.csv");
         InputStreamReader isr = new InputStreamReader(fis);
         BufferedReader br = new BufferedReader(isr);
         br.readLine();// Fields
         while(br.ready()) {
             em.getTransaction().begin();
             StringTokenizer st = new StringTokenizer(br.readLine(),",");
             RegionData rd = new RegionData();
             RegionDataPK pk = new RegionDataPK();
             pk.setIndustry(industry);
             rd.setSa4name(st.nextToken());
             pk.setAnzsco(st.nextToken());
             pk.setSa4code(st.nextToken());
             rd.setRegionDataPK(pk);
             rd.setUnempRateMvmt(Double.parseDouble(st.nextToken()));
             rd.setUnempRateScore(Integer.parseInt(st.nextToken()));
             rd.setAverageAnnualMovement(Double.parseDouble(st.nextToken()));
             rd.setAverageAnnualMovementScore(Integer.parseInt(st.nextToken()));
             rd.setHousePrice(Double.parseDouble(st.nextToken()));
             rd.setRentPrice(Double.parseDouble(st.nextToken()));
             rd.setHousePriceScore(Integer.parseInt(st.nextToken()));
             rd.setRentPriceScore(Integer.parseInt(st.nextToken()));
             rd.setMedianIncomel(Double.parseDouble(st.nextToken()));
             rd.setMedianIncomeScore(Integer.parseInt(st.nextToken()));
             //st.nextToken();
             //st.nextToken();

             rd.setPatentIncrease(Double.parseDouble(st.nextToken()));
             rd.setTradeMarksIncrease(Double.parseDouble(st.nextToken()));
             rd.setNewBusinessIncrease(Double.parseDouble(st.nextToken()));
             //rd.setNumBus1315Mvmt(Double.parseDouble(st.nextToken()));
             //rd.setNumBus1315MvmtScore(Integer.parseInt(st.nextToken()));
             rd.setPatentIncreaseScore(Integer.parseInt(st.nextToken()));
             rd.setTradeMarksScore(Integer.parseInt(st.nextToken()));
             rd.setNewBusinessScore(Integer.parseInt(st.nextToken()));
             rd.setUnempRateMvmt(Double.parseDouble(st.nextToken()));
             rd.setUnempRateScore(Integer.parseInt(st.nextToken()));
             rd.setTotalBusinesses13(Double.parseDouble(st.nextToken()));
             rd.setTotalBusinesses14(Double.parseDouble(st.nextToken()));
             rd.setIncreaseBusinesses(Double.parseDouble(st.nextToken()));
             rd.setIncreaseBusScore(Integer.parseInt(st.nextToken()));
             st.nextToken();//all_sa4_codes
             st.nextToken(); // nswscore
             st.nextToken(); // state
             rd.setJobVacancyMvmt(Double.parseDouble(st.nextToken()));
             rd.setJobVacancyScore(Integer.parseInt(st.nextToken()));
             rd.setOccUnemp1315(Double.parseDouble(st.nextToken()));
             rd.setOccUnemp1315Score(Integer.parseInt(st.nextToken()));
             rd.setNumBus1315Mvmt(Double.parseDouble(st.nextToken()));
             rd.setNumBus1315Score(Integer.parseInt(st.nextToken()));
             em.persist(rd);
             rd = null;
             em.getTransaction().commit();
             em.clear();
         }
         
    }
}
