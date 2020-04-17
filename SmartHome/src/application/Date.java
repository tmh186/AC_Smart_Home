package application;

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
	
	public Date() {
		
	}
	
	public Date(int day, int month, int year) {
		setDay(day);
		setMonth(month);
		setYear(year);
	}
	
	public Date(Double water, Double electricity) {
		setWater(water);
		setElectricity(electricity);
		setTotal(calcTotal(getElectricity(), getWater()));
	}
	
	public Date(int day, int month, int year, Double water, Double electricity) {
		setDay(day);
		setMonth(month);
		setYear(year);
		setWater(water);
		setElectricity(electricity);
		setTotal(calcTotal(getElectricity(), getWater()));
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
	
	public Double calcTotal(Double electricity, Double water) {
		return electricity+water;
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

}
