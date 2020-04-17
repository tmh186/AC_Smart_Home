package application;

import connections.Bill;

public class Date {
	//date info
	public int day;
	public int month;
	public int year;
	//bill info for 1 day
	public Double water;
	public Double electricity;
	public Double total;
	
	public Double temp;
	//bill time frame (used if adding multiple date data to graph)
	public String timeframeString;
	public Bill bill;
	
	public Date() {
		setWater(0.0);
		setElectricity(0.0);
		setTotal(0.0);
	}
	
	public Date(int day, int month, int year) {
		setDay(day);
		setMonth(month);
		setYear(year);
	}
	
	public Date(Double water, Double electricity) {
		setWater(water);
		setElectricity(electricity);
		setTotal(calcTotal());
	}
	
	public Date(int day, int month, int year, Double water, Double electricity) {
		setDay(day);
		setMonth(month);
		setYear(year);
		setWater(water);
		setElectricity(electricity);
		setTotal(calcTotal());
	}
	
	public Date(Bill b, Double water, Double electricity) {
		setWater(water);
		setElectricity(electricity);
		setTotal(bill.getTotal());
	}
	
	public void setDay(int d) {
		day=d;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setMonth(int m) {
		month=m;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setYear(int y) {
		year=y;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setWater(Double w) {
		water=w;
	}
	
	public Double getWater() {
		return water;
	}
	
	public void setElectricity(Double e) {
		electricity=e;
	}
	
	public Double getElectricity() {
		return electricity;
	}
	
	public void setTotal(Double t) {
		total=t;
	}
	
	public Double getTotal() {
		return total;
	}
	
	public Double calcTotal() {
		return electricity+water;
	}
	
	public void setBill(Bill b) {
		bill = b;
	}
	
	public Bill getBill() {
		return bill;
	}
	
	public void setTemp(Double t) {
		temp=t;
	}
	
	public Double getTemp() {
		return temp;
	}
	
	public String dateToString() {
		return month+"/"+day;
	}
	
	public void setTimeFrameString(String s) {
		timeframeString=s;
	}
	
	public String getTimeFrameString() {
		return timeframeString;
	}

}
