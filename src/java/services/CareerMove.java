package services;


import entities.RegionData;
import java.net.MalformedURLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import sdmx.Queryable;
import sdmx.Registry;
import sdmx.Repository;
import sdmx.net.ServiceList;
import sdmx.net.list.DataProvider;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author James
 */
public class CareerMove {
    public static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("GovhackPU");
    public static EntityManager EM = EMF.createEntityManager();
    
    public static DataProvider dp = null;
    public static Queryable queryable = null;
    public static Registry reg = null;
    public static Repository rep = null;
    public static final Object lock = new Object();
    /**
     * @param args the command line arguments
     */
static {
            try {
                dp = ServiceList.getDataProvider(0, "ABS", "http://stat.abs.gov.au/sdmxws/sdmx.asmx", "http://stats.oecd.org/OECDStatWS/SDMX/", "Based on Australian Bureau of Statistics data", "Based on Australian Bureau of Statistics data");
            } catch (MalformedURLException mfe) {
            }
            queryable = dp.getQueryable();
            reg = queryable.getRegistry();
            rep = queryable.getRepository();
        }
  public CareerMove() {
  }
  public static RegionData findRegionData(String sa4,String industry,String anzsco) {
      Query q = EM.createQuery("select r from RegionData r where r.regionDataPK.industry=:industry and r.regionDataPK.sa4code=:sa4 and r.regionDataPK.anzsco=:anzsco");
      q.setParameter("industry",industry);
      q.setParameter("anzsco",anzsco);
      q.setParameter("sa4", sa4);
      return (RegionData)q.getSingleResult();
  }
}
