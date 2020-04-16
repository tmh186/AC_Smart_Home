package connections;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	/**
	 * This will return the bill that is already in the database for this day 
	 * or it will return a new bill for this current date
	 * @param bills , list of all current bills in the database
	 * @return the appropriate bill for the day
	 * @throws SQLException
	 */
	public static Bill getCurrentBill(ArrayList<Bill> bills) throws SQLException {
		String cDate = Database.getCurrentDate();
		for (int i = 0; i < bills.size(); i++) {
			if (bills.get(i).getDate().equals(Date.valueOf(cDate))) {
				return bills.get(i);
			}
		}
		return new Bill(Date.valueOf(cDate), 0.00, 0.00);
		
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
