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
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import sdmx.Queryable;
import sdmx.Registry;
import sdmx.Repository;
import sdmx.commonreferences.DataStructureReference;
import sdmx.commonreferences.IDType;
import sdmx.commonreferences.NestedNCNameID;
import sdmx.commonreferences.Version;
import sdmx.data.DataSet;
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
import sdmx.util.time.TimeUtil;
import sdmx.version.common.ParseParams;

/**
 *
 * @author James
 */
public class GovHack1 extends JFrame {

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
        /*
        try {
            saveJPEG("501", new FileOutputStream("out1.png"));
        } catch (Exception ex) {
            Logger.getLogger(GovHack1.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    public GovHack1(String title) {
        super(title);
        setSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static StructuredDataMessage getSDM1(String region1,String region2,String industry) {
        try {

            DataflowType flow = new DataflowType();
            flow.setId(new IDType("ABS_EIE"));
            flow.setStructure(DataStructureReference.create(new NestedNCNameID("ABS"), new IDType("ABS_EIE"), Version.ONE));
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
            dw.setTimeDimensionValue(Collections.singletonList(new TimeDimensionValueType(new TimeValue("2010-01-01"), new TimeValue("2017-01-01"))));
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
                if (dim.getId().equals("ANZSIC")) {
                    dims.add(new DimensionValueType("ANZSIC", industry));
                }
                if (dim.getId().equals("REGION")) {
                    dims.add(new DimensionValueType("REGION", region1));
                    dims.add(new DimensionValueType("REGION", region2));
                }
                if (dim.getId().equals("MEASURE")) {
                    
                }
                or.setDimensionValue(dims);
                ors.add(or);
            }
            DataParametersOrType or2 = new DataParametersOrType();
            or2.setDimensionValue(Collections.singletonList(new DimensionValueType("MEASURE", "ITO")));
            ors.add(or2);
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
            }
            return sdm;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void saveJPEG(String region1,String region2,String industry, OutputStream out) throws Exception {
        //GovHack1 gv = new GovHack1("title");
        StructuredDataMessage sdm = getSDM1(region1,region2,industry);
        JFreeChart chart = ChartFactory.createXYLineChart("Industry Turnover", "Value", "Time", getDataSet(region1,region2, sdm));
        XYPlot xyplot = (XYPlot) chart.getPlot();
        xyplot.setForegroundAlpha(0.65F);
        XYItemRenderer xyitemrenderer = xyplot.getRenderer();
        xyitemrenderer.setSeriesPaint(10, Color.blue);

        try {
            ChartUtilities.writeChartAsPNG(out, chart, 600, 600);
        } catch (IOException ex) {
            Logger.getLogger(GovHack1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static XYDataset getDataSet(String region1, String region2, StructuredDataMessage sdm) {
        TimeSeriesCollection col = new TimeSeriesCollection();
        final TimeSeries series1 = new TimeSeries("Your SA4");
        final TimeSeries series2 = new TimeSeries("Selected SA4");
        StructuredDataSet ds = sdm.getStructuredDataSet(0);
        DataSet ds2 = ds.getDataSet();
        for (int i = 0; i < ds.size(); i++) {
            if (region1.equals(ds.getStructuredValue(i, ds2.getColumnIndex("REGION")).getValue())) {
                String r1 =NameableType.toString( ds.getStructuredValue(i,ds2.getColumnIndex("REGION")).getCode());
                String time = ds.getStructuredValue(i, ds2.getColumnIndex("TIME")).getValue();
                RegularTimePeriod rtp = TimeUtil.parseTime("A", time);
                Double value = Double.parseDouble(ds.getStructuredValue(i, ds2.getColumnIndex("OBS_VALUE")).getValue());
                series1.setDescription(r1);
                series1.add(rtp, value);
            }
            if (region2.equals(ds.getStructuredValue(i, ds2.getColumnIndex("REGION")).getValue())) {
                String r2 =NameableType.toString( ds.getStructuredValue(i,ds2.getColumnIndex("REGION")).getCode());
                String time = ds.getStructuredValue(i, ds2.getColumnIndex("TIME")).getValue();
                RegularTimePeriod rtp = TimeUtil.parseTime("A", time);
                Double value = Double.parseDouble(ds.getStructuredValue(i,ds2.getColumnIndex("OBS_VALUE")).getValue());
                series2.setDescription(r2);
                series2.add(rtp, value);
            }
        }
        col.addSeries(series1);
        col.addSeries(series2);
        return col;
    }
}
