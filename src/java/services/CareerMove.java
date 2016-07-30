package services;


import java.net.MalformedURLException;
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
}
