package com.restaurant.serviceBeans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.LinearAxis;
import org.springframework.context.annotation.Scope;

import com.restaurant.entities.Report;
import com.restaurant.services.AnalystService;

@Named
@Scope("session")
public class ReportBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Date startDate;
	private Date endDate;
	private List<Report> reports;
	private LineChartModel chartModel;
    private LineChartModel multiAxisModel;
	@Inject
	private AnalystService analistServ; 
	
	@PostConstruct
	public void init(){
		startDate = new Date();
		endDate = new Date();
	}
	
	public ReportBean() {}
	
	public String getReportByCountAndSum(){
		reports = null;
		reports = analistServ.getReportByCountAndSum(startDate, endDate);
		createMultiAxisModel();
		return "/staffPages/Analyst-ReportByCountAndSum?faces-redirect=true";
	}
	
	public String getReportByMenuItemCatogories(){
		reports = null;
		reports = analistServ.getReportByMenuItemCatogories(startDate, endDate);
		createChartModel();
		return "/staffPages/Analyst-ReportByCatAndCount?faces-redirect=true";
	}
	
	public void createChartModel(){
		chartModel = new LineChartModel();
        
		BarChartSeries countOfOrders = new BarChartSeries();
		countOfOrders.setLabel("Total count of orders");
        
        if(reports!=null){
        	for(Report e: reports){
        		countOfOrders.set(e.getNameOfCategory(), e.getCount());
        	}
        }
        
        LineChartSeries sumOfOrders = new LineChartSeries();
        sumOfOrders.setLabel("Total sum");
        sumOfOrders.setXaxis(AxisType.X);
        sumOfOrders.setYaxis(AxisType.Y2);
        
        if(reports!=null){
        	for(Report e: reports){
        		sumOfOrders.set(e.getNameOfCategory(), e.getOrdersSum());
        	}
        }
        
        chartModel.addSeries(countOfOrders);
        chartModel.addSeries(sumOfOrders);
 
        chartModel.setTitle("Report by categories");
        chartModel.setMouseoverHighlight(false);
        
        chartModel.getAxes().put(AxisType.X, new CategoryAxis("Category"));

        Axis yAxis = chartModel.getAxis(AxisType.Y);
        yAxis.setLabel("Quantity");
        yAxis.setMin(0);
        yAxis.setMax(50);
        yAxis.setTickFormat("%.0f");
        
        Axis y2Axis = new LinearAxis("Total sum");
        y2Axis.setMin(0);
        y2Axis.setMax(500);
        y2Axis.setTickFormat("%.2f");

        chartModel.getAxes().put(AxisType.Y2, y2Axis);
    }
	
	private void createMultiAxisModel() {
        multiAxisModel = new LineChartModel();
 
        BarChartSeries quantity = new BarChartSeries();
        quantity.setLabel("Quantity");
 
        if(reports!=null){
        	for(Report e:reports){
        		DateFormat df = new SimpleDateFormat("dd.MM");
        		quantity.set(df.format(e.getDate()), e.getCount());
        	}
        }
 
        LineChartSeries sum = new LineChartSeries();
        sum.setLabel("Total sum");
        sum.setXaxis(AxisType.X);
        sum.setYaxis(AxisType.Y2);
         
        if(reports!=null){
        	for(Report e:reports){
        		DateFormat df = new SimpleDateFormat("dd.MM");
        		sum.set(df.format(e.getDate()), e.getOrdersSum());
        	}
        }
        multiAxisModel.addSeries(quantity);
        multiAxisModel.addSeries(sum);
         
        multiAxisModel.setTitle("Report by count and sum");
        multiAxisModel.setMouseoverHighlight(false);
         
        multiAxisModel.getAxes().put(AxisType.X, new CategoryAxis("Date"));
//        multiAxisModel.getAxes().put(AxisType.X2, new CategoryAxis("Period"));
         
        Axis yAxis = multiAxisModel.getAxis(AxisType.Y);
        yAxis.setLabel("Quantity");
        yAxis.setMin(0);
        yAxis.setMax(50);
        yAxis.setTickFormat("%.0f");

        Axis y2Axis = new LinearAxis("Total sum");
        y2Axis.setMin(0);
        y2Axis.setMax(1000);
        y2Axis.setTickFormat("%.2f");

        multiAxisModel.getAxes().put(AxisType.Y2, y2Axis);

    }
	
	public String back(){
		startDate = null;
		endDate = null;
		reports.clear();
		return "/staffPages/Analyst-Main?faces-redirect=true";
	}
	
	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public LineChartModel getChartModel() {
		return chartModel;
	}

	public LineChartModel getMultiAxisModel() {
		return multiAxisModel;
	}
	
	public Date getToday(){
        return new Date();
	}

}
