package services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.Axis;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import static org.jfree.chart.plot.PlotOrientation.HORIZONTAL;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.urls.StandardXYURLGenerator;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.IntervalXYDataset;
import sdmx.Queryable;
import sdmx.Registry;
import sdmx.Repository;
import sdmx.commonreferences.DataStructureReference;
import sdmx.commonreferences.IDType;
import sdmx.commonreferences.NestedNCNameID;
import sdmx.commonreferences.Version;
import sdmx.data.flat.FlatObs;
import sdmx.data.structured.Obs;
import sdmx.exception.ParseException;
import sdmx.message.DataMessage;
import sdmx.message.DataQueryMessage;
import sdmx.net.ServiceList;
import sdmx.net.list.DataProvider;
import sdmx.query.base.TimeValue;
import sdmx.query.data.DataParametersAndType;
import sdmx.query.data.DataParametersOrType;
import sdmx.query.data.DataParametersType;
import sdmx.query.data.DataQuery;
import sdmx.query.data.DimensionValueType;
import sdmx.query.data.TimeDimensionValueType;
import sdmx.structure.base.ComponentUtil;
import sdmx.structure.base.ItemType;
import sdmx.structure.base.NameableType;
import sdmx.structure.codelist.CodeType;
import sdmx.structure.codelist.CodelistType;
import sdmx.structure.dataflow.DataflowType;
import sdmx.structure.datastructure.DataStructureType;
import sdmx.structure.datastructure.DimensionType;
import sdmx.structureddata.StructuredDataMessage;
import sdmx.structureddata.StructuredDataSet;
import sdmx.structureddata.StructuredValue;
import sdmx.version.common.ParseParams;

/**
 *
 * @author James
 */
public class GovHack2 extends JFrame {

    public static String STATE = "5";

    public static DataProvider dp = CareerMove.dp;
    public static Queryable queryable = CareerMove.queryable;
    public static Registry reg = CareerMove.reg;
    public static Repository rep = CareerMove.rep;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException {
        {
            try {
                dp = ServiceList.getDataProvider(0, "ABS", "http://stat.abs.gov.au/sdmxws/sdmx.asmx", "http://stats.oecd.org/OECDStatWS/SDMX/", "Based on Australian Bureau of Statistics data", "Based on Australian Bureau of Statistics data");
            } catch (MalformedURLException mfe) {
            }
            queryable = dp.getQueryable();
            reg = queryable.getRegistry();
            rep = queryable.getRepository();
        }

        try {
            saveJPEG("501", new FileOutputStream("out1.png"));
        } catch (Exception ex) {
            Logger.getLogger(GovHack2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public GovHack2(String title) {
        super(title);
        setSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static StructuredDataMessage getSDM1(String region) {
        try {

            DataflowType flow = new DataflowType();
            flow.setId(new IDType("ABS_CENSUS2011_T24"));
            flow.setStructure(DataStructureReference.create(new NestedNCNameID("ABS"), new IDType("ABS_CENSUS2011_T24"), Version.ONE));
            // flow.getStructure() returns a DataStructureReference which is only a reference
            // a reference is just a set of IDs used to find the concrete instance of DataStructureType
            DataStructureType struct = reg.find(flow.getStructure());
            // struct contains information about this dataflow's structure

            DataQueryMessage query = new DataQueryMessage();
            // Ignore the Header, it will be filled in by the driver
            // You can set the Header fields yourself if you wish, and the driver
            // will not overwrite them
            DataQuery q = new DataQuery();
            DataParametersAndType dw = new DataParametersAndType();
            List<DataParametersOrType> ors = new ArrayList<DataParametersOrType>();
            // This sets which cube we want to query...
            // some queryables fudge the dataflow name (like SDW)
            // as SDW does not have dataflows, only a list of datastructures
            dw.setDataflow(Collections.singletonList(flow.asReference()));
            // hard coded times
            // year-month-day
            dw.setTimeDimensionValue(Collections.singletonList(new TimeDimensionValueType(new TimeValue("2010-01-01"), new TimeValue("2014-01-01"))));
            /*
            This section goes through each dimension, struct.getDataStructureComponents().getDimensionList().getDimension(i)
            finds the codelist for that dimension   Dimension.getEnumeration().asCodelistReference()
            (getEnumeration returns an ItemSchemeReference 'asCodelistReference' is sort of like casting to a different class)
            it finds the very first code listed in the codelist, and adds it as a query parameter to the query...
             */
            for (int i = 0; i < struct.getDataStructureComponents().getDimensionList().size(); i++) {
                DimensionType dim = struct.getDataStructureComponents().getDimensionList().getDimension(i);
                DataParametersOrType or = new DataParametersOrType();
                List<DimensionValueType> dims = new ArrayList<DimensionValueType>();
                CodelistType codes1 = reg.find(ComponentUtil.getRepresentation(reg, struct.getDataStructureComponents().getDimensionList().getDimension(i)).getEnumeration().asCodelistReference());
                //if (dim.getId().equals("STATE")) {
                //    dims.add(new DimensionValueType("STATE", STATE));
                //}
                //if (dim.getId().equals("REGIONTYPE")) {
                //    dims.add(new DimensionValueType("RExGIONTYPE", "SA2"));
                // }
                //if (dim.getId().equals("REGION")) {
                //    dims.add(new DimensionValueType("REGION", sa));
                //}
                if (dim.getId().equals("FREQUENCY")) {
                    dims.add(new DimensionValueType("FREQUENCY", "A"));
                }
                if (dim.getId().equals("REGION")) {
                    dims.add(new DimensionValueType("REGION", region));
                }
                or.setDimensionValue(dims);
                ors.add(or);
            }
            //DataParametersOrType or2 = new DataParametersOrType();
            //or2.setDimensionValue(Collections.singletonList(new DimensionValueType("MEASURE", "TOT")));
            //ors.add(or2);
            dw.setOr(ors);
            DataParametersType dpt = new DataParametersType();
            // Some Providers require another "AND" query element to be under the main DataParametersType(which is an And)
            // so we put everything into dw and set it here
            dpt.setAnd(Collections.singletonList(dw));
            q.setDataWhere(dpt);
            query.setQuery(q);
            long t3 = System.currentTimeMillis();
            ParseParams params = new ParseParams();
            params.setDataflow(flow);
            params.setRegistry(reg);
            DataMessage dm = null;
            try {
                dm = rep.query(params, query);
            } catch (ParseException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            long t4 = System.currentTimeMillis();
            System.out.println("Got CompactData " + dm.getDataSets().get(0).size() + " observations " + (t4 - t3) + " ms");
            // Structured Data Message needs a registry to find Structural Data
            dm.setDataStructure(flow.getStructure(), null);
            StructuredDataMessage sdm = new StructuredDataMessage(dm, reg);
            StructuredDataSet sds = sdm.getStructuredDataSet(0);
            /*
            for (int i = 0; i < sdm.getStructuredDataSet(0).size(); i++) {
                for (int j = 0; j < sdm.getStructuredDataSet(0).getColumnCount(); j++) {
                    ItemType c = sdm.getStructuredDataSet(0).getStructuredValue(i, j).getCode();
                    if (c != null) {
                        System.out.print(NameableType.toString(c));
                    } else {
                        System.out.print(sdm.getStructuredDataSet(0).getStructuredValue(i, j).getValue());
                    }
                    System.out.print("\t");
                }
                System.out.println();
            }*/
            return sdm;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void saveJPEG(String region, OutputStream out) throws Exception {
        StructuredDataMessage sdm = getSDM1(region);
        JFreeChart chart = ChartFactory.createBubbleChart(
                "Household Rent vs Household Income",
                "Rent",
                "Income",
                createDataset1(region, sdm),
                PlotOrientation.HORIZONTAL,
                true, true, false);
        XYPlot xyplot = (XYPlot) chart.getPlot();
        xyplot.setForegroundAlpha(0.65F);
        XYItemRenderer xyitemrenderer = xyplot.getRenderer();
        xyitemrenderer.setSeriesPaint(10, Color.blue);

        try {
            ChartUtilities.writeChartAsPNG(out, chart, 600, 600);
        } catch (IOException ex) {
            Logger.getLogger(GovHack2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates a sample {@link HistogramDataset}.
     *
     * @return the dataset.
     */
    private static DefaultXYZDataset createDataset1(String sa, StructuredDataMessage sdm1) {
        DefaultXYZDataset defaultxyzdataset = new DefaultXYZDataset();
        Double[][] result = getResult(sa, sdm1);
        double res[][] = new double[3][];
        for (int i = 0; i < 3; i++) {
            res[i] = new double[result[i].length];
            for (int j = 0; j < result[i].length; j++) {
                res[i][j] = result[i][j];
            }
        }
        double ad3[][] = {res[0], res[1], res[2]};
        String satitle = "";
        for (int i = 0; i < sdm1.getStructuredDataSet(0).size(); i++) {
            if (sdm1.getStructuredDataSet(0).getStructuredValue(i, sdm1.getDataMessage().getDataSets().get(0).getColumnIndex("REGION")).getCode().getId().equals(sa)) {
                satitle = NameableType.toString(sdm1.getStructuredDataSet(0).getStructuredValue(i, sdm1.getDataMessage().getDataSets().get(0).getColumnIndex("REGION")).getCode());
                break;
            }
        }
        defaultxyzdataset.addSeries(satitle, ad3);
        return defaultxyzdataset;
    }

    private static Double[][] getResult(String sa, StructuredDataMessage sdm) {
        List<Double> xvalues = new ArrayList<Double>();
        List<Double> yvalues = new ArrayList<Double>();
        List<Double> zvalues = new ArrayList<Double>();
        double xvt = 0.0d;
        double yvt = 0.0d;
        double zvt = 0.0d;

        for (int i = 0; i < 11; i++) {
            String lookingFor1 = "";
            switch (i) {
                case 0:
                    lookingFor1 = "01_02";
                    xvt = 37d;
                    break;
                case 1:
                    lookingFor1 = "03";
                    xvt = 87d;
                    break;
                case 2:
                    lookingFor1 = "04_05";
                    xvt = 124.5d;
                    break;
                case 3:
                    lookingFor1 = "06_07";
                    xvt = 174.5d;
                    break;
                case 4:
                    lookingFor1 = "08";
                    xvt = 212d;
                    break;
                case 5:
                    lookingFor1 = "09_10";
                    xvt = 249.5d;
                    break;
                case 6:
                    lookingFor1 = "11_13";
                    xvt = 312d;
                    break;
                case 7:
                    lookingFor1 = "14_17";
                    xvt = 399.5d;
                    break;
                case 8:
                    lookingFor1 = "18";
                    xvt = 499.5d;
                    break;
                case 9:
                    lookingFor1 = "19";
                    xvt = 599.5d;
                    break;
                case 10:
                    lookingFor1 = "20";
                    xvt = 750.0d;
                    break;
            }
            List<FlatObs> list = new ArrayList<FlatObs>();
            for (int j = 0; j < sdm.getStructuredDataSet(0).size(); j++) {
                if (sdm.getStructuredDataSet(0).getStructuredValue(j, sdm.getStructuredDataSet(0).getDataSet().getColumnIndex("MEASURE")).getValue().equals(lookingFor1) && sdm.getStructuredDataSet(0).getDataSet().getValue(j, sdm.getStructuredDataSet(0).getDataSet().getColumnIndex("REGION")).equals(sa)) {
                    int columnCount = sdm.getStructuredDataSet(0).getColumnCount();
                    //System.out.println("Val:"+x+"="+sdm.getStructuredDataSet(0).getStructuredValue(i,sdm.getDataMessage().getDataSets().get(0).getColumnMapper().getColumnIndex("OBS_VALUE")).getValue());
                    list.add(sdm.getStructuredDataSet(0).getDataSet().getFlatObs(j));
                }
            }
            for (int k = 0; k < 13; k++) {
                String lookingFor = "";
                switch (k) {
                    case 0:
                        lookingFor = "01_02";
                        yvt = 0.0d;
                        break;
                    case 1:
                        lookingFor = "03";
                        yvt = 100.0d;
                        break;
                    case 2:
                        lookingFor = "04";
                        yvt = 250.0d;
                        break;
                    case 3:
                        lookingFor = "05";
                        yvt = 350.0d;
                        break;
                    case 4:
                        lookingFor = "06";
                        yvt = 500.0d;
                        break;
                    case 5:
                        lookingFor = "07";
                        yvt = 700.0d;
                        break;
                    case 6:
                        lookingFor = "08";
                        yvt = 900.0d;
                        break;
                    case 7:
                        lookingFor = "09";
                        yvt = 1125.0d;
                        break;
                    case 8:
                        lookingFor = "10";
                        yvt = 1325.0d;
                        break;
                    case 9:
                        lookingFor = "11";
                        yvt = 1750.0d;
                        break;
                    case 10:
                        lookingFor = "12";
                        yvt = 2250.0d;
                        break;
                    case 11:
                        lookingFor = "13";
                        yvt = 2750.0d;
                        break;
                    case 12:
                        lookingFor = "14_17";
                        yvt = 3250.0d;
                        break;
                }
                for (int l = 0; l < list.size(); l++) {
                    if (lookingFor.equals(list.get(l).getValue(sdm.getDataMessage().getDataSets().get(0).getColumnIndex("FINF")))) {
                        //System.out.println("Val:"+x+"="+sdm.getStructuredDataSet(0).getStructuredValue(i,sdm.getDataMessage().getDataSets().get(0).getColumnMapper().getColumnIndex("OBS_VALUE")).getValue());
                        xvalues.add(xvt);
                        yvalues.add(yvt);
                        zvt = Double.parseDouble(list.get(l).getValue(sdm.getDataMessage().getDataSets().get(0).getColumnMapper().getColumnIndex("OBS_VALUE")));
                        zvalues.add(Double.parseDouble(list.get(l).getValue(sdm.getDataMessage().getDataSets().get(0).getColumnMapper().getColumnIndex("OBS_VALUE"))));
                        //System.out.println("x,y,z=" + xvt + "," + yvt + "," + zvt);
                    }
                }
            }
        }
        Double xv[] = (Double[]) xvalues.toArray(new Double[]{});
        Double yv[] = (Double[]) yvalues.toArray(new Double[]{});
        Double zv[] = (Double[]) zvalues.toArray(new Double[]{});

        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        double tot = 0.0d;

        for (int i = 0; i < zv.length; i++) {
            if (zv[i] < min) {
                min = zv[i];
            }
            if (zv[i] > max) {
                max = zv[i];
            }
            tot += zv[i];
            zv[i] = zv[i] * 10;
        }
        double range = max - min;
        for (int i = 0; i < zv.length; i++) {
            zv[i] = ((zv[i]) / tot) * 100000d;
        }
        for (int i = 0; i < zv.length; i++) {
            zv[i] = Math.sqrt(zv[i] / Math.PI) * 2;
        }
        Double result[][] = {xv, yv, zv};
        return result;
    }
}
