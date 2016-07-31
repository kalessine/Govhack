/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.RegionData;
import java.awt.Color;
import java.awt.GradientPaint;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.Year;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author Owner
 */
public class TotalBusinesses {

    public static void savePNG(String left, String right, String industry, String anzsco,OutputStream out) throws IOException {
        JFreeChart chart = createChart(createDataset(left, right, industry, anzsco));
        try {
            ChartUtilities.writeChartAsPNG(out, chart, 600, 600);
        } catch (Exception ex) {
            IOUtils.copy(CareerMove.class.getResourceAsStream("error.png"), out);
            Logger.getLogger(GovHack1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return;
    }

    public static TimeSeriesCollection createDataset(String left, String right, String industry, String anzsco) {
        RegionData leftRD = CareerMove.findRegionData(left, industry, anzsco);
        RegionData rightRD = CareerMove.findRegionData(right, industry, anzsco);
        TimeSeries series1 = new TimeSeries(leftRD.getSa4name());
        TimeSeries series2 = new TimeSeries(rightRD.getSa4name());
        TimeSeriesCollection col = new TimeSeriesCollection();
        series1.add(new Year(2013), leftRD.getTotalBusinesses13());
        series1.add(new Year(2014), leftRD.getTotalBusinesses14());
        series1.add(new Year(2013), rightRD.getTotalBusinesses13());
        series1.add(new Year(2014), rightRD.getTotalBusinesses14());
        col.addSeries(series1);
        col.addSeries(series2);
        return col;
    }

    public static JFreeChart createChart(TimeSeriesCollection col) {
JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Total Businesses 2013,2014",  // title
            "Time",             // x-axis label
            "Number",   // y-axis label
            col,            // data
            true,               // create legend?
            true,               // generate tooltips?
            false               // generate URLs?
        );


        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(false);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(false);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        return chart;
    }

}

