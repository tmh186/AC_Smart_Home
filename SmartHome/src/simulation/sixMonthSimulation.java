package simulation;

import connections.Bill;
import connections.Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class sixMonthSimulation {

	// private static Connection

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("DailyTemp.csv"));
		Simulations s = new Simulations();
		String row;
		String[] elms, elms2;
		int count = 1;

		ArrayList<Bill> bills = new ArrayList<Bill>();
		ArrayList<Integer> temps = new ArrayList<Integer>();

		row = br.readLine();
		row = br.readLine();
		int count1 = 0;
		
		Calendar cal = Calendar.getInstance();
		while (row != null) {
			elms = row.split(",");
			temps.add(Integer.parseInt(elms[2]));
			elms2 = elms[0].split("/");
//			System.out.println("Date: "+  elms[0]);
			count1++;
			if (count1 == 24) {
				count++;
				
				
				
				// Use calendar to get date and get current time mills to avoid using the deprecated Date(Y, m, d) constructor
				// also interchange the day for month
				cal.set(Integer.parseInt(elms2[2]) , Integer.parseInt(elms2[0])-1, Integer.parseInt(elms2[1]));
				Date d = new Date(cal.getTimeInMillis());
				if (count < 6) {
					bills.add(createBill(d, temps, "weekday"));
				}
				if (count >= 6 && count < 8) {
					bills.add(createBill(d, temps, "weekend"));
					if (count + 1 == 8) {
						count = 0;
					}
				}
				count1 = 0;
			}

			row = br.readLine();

		}
		br.close();

		
		// updating the data base with bill created 
		System.out.println(bills);
		Connection c = Database.initConnect();
		try {
			for (Bill bill : bills) {
				Database.updateBillArchive(c, bill);
			}
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Bill createBill(Date date, ArrayList<Integer> temps, String days) {

		double waterCost;
		double powerCost = 0;
		Simulations s = new Simulations();
		HVAC h = new HVAC();
		int count = 0;

		waterCost = s.waterCost(days);
		powerCost += s.powerCostFixed(days);
		powerCost += s.calculateRandomPowerCost(s.randomPowerEvents(days));
		powerCost += h.hvacCost(h.hvacHours(temps, days));

		return (new Bill(date, powerCost, waterCost));

	}
}
