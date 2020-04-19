package connections;

import java.sql.Date;

public class Bill {

	private double totalElec;
	private double totalWater;
	private Date date;

	public Bill(Date date, double water, double elec) {
		// TODO Auto-generated constructor stub
		this.date = date;
		this.totalElec = elec;
		this.totalWater = water;
	}
	
	public void addWater(double value) {
		this.totalWater += value;
	}
	
	public void addElec(double value) {
		this.totalElec += value;
	}

	public double getTotal() {
		return totalElec + totalWater;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotalElec() {
		return totalElec;
	}

	public void setTotalElec(double totalElec) {
		this.totalElec = totalElec;
	}

	public double getTotalWater() {
		return totalWater;
	}

	public void setTotalWater(double totalWater) {
		this.totalWater = totalWater;
	}

	@Override
	public String toString() {
		return "Bill [totalElec=" + totalElec + ", totalWater=" + totalWater + ", date=" + date + "]";
	}

}
