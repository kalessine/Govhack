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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Owner
 */
public class UnempRate {

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

    public static CategoryDataset createDataset(String left, String right, String industry, String anzsco) {
        RegionData leftRD = CareerMove.findRegionData(left, industry, anzsco);
        RegionData rightRD = CareerMove.findRegionData(right, industry, anzsco);
        String s = leftRD.getSa4name();
        String s1 = rightRD.getSa4name();
        String s2 = "";
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        defaultcategorydataset.addValue(leftRD.getUnempRateMvmt(), s, s2);
        defaultcategorydataset.addValue(rightRD.getUnempRateMvmt(), s1, s2);
        return defaultcategorydataset;
    }

    public static JFreeChart createChart(CategoryDataset categorydataset) {
        JFreeChart jfreechart = ChartFactory.createBarChart("Unemployment Movement", "Region", "Rate", categorydataset, PlotOrientation.VERTICAL, true, true, false);
        jfreechart.setBackgroundPaint(new Color(0xffffff));
        CategoryPlot categoryplot = jfreechart.getCategoryPlot();
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
        if( numberaxis.getUpperBound()==0 ) {
            numberaxis.setUpperBound(Math.abs(numberaxis.getLowerBound())*.1d);
        }
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer();
        StandardBarPainter painter = new StandardBarPainter();
        barrenderer.setBarPainter(painter);
        barrenderer.setDrawBarOutline(false);
        barrenderer.setMinimumBarLength(0.10000000000000001D);
        GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, Color.lightGray);
        GradientPaint gradientpaint1 = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, Color.lightGray);
        barrenderer.setSeriesPaint(0, gradientpaint);
        barrenderer.setSeriesPaint(1, gradientpaint1);
        return jfreechart;
    }

}
