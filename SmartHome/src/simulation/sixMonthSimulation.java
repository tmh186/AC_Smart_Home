package simulation;
import connections.Bill;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;

public class sixMonthSimulation {
	
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
		while (row != null) {
			elms = row.split(",");
			temps.add(Integer.parseInt(elms[2]));
			elms2 = elms[0].split("/");
			count1 ++;
			if (count1 == 24) {
				count ++;
				if (count < 6) {
					bills.add(createBill(new Date(Integer.parseInt(elms2[2]) - 1900, Integer.parseInt(elms2[1]) - 1, Integer.parseInt(elms2[0])), temps, "weekday"));
				}
				if (count >= 6 && count < 8) {
					bills.add(createBill(new Date(Integer.parseInt(elms2[2]) - 1900, Integer.parseInt(elms2[1]) - 1, Integer.parseInt(elms2[0])), temps, "weekend"));
					if (count + 1 == 8) {
						count = 0;
					}
				}
				count1 = 0;
			}
			
		row = br.readLine();
			
		}
		br.close();
		
		System.out.println(bills);
		
	}
	
	public static Bill createBill(Date date, ArrayList<Integer> temps, String days) {
		
		double waterCost;
		double powerCost =0;
		Simulations s = new Simulations();
		HVAC h = new HVAC();
		int count = 0;
		
		waterCost = s.waterCost(days);
		powerCost += s.powerCostFixed(days);
		powerCost += s.calculateRandomPowerCost(s.randomPowerEvents(days));
		powerCost += h.hvacCost(h.hvacHours(temps, days));
		
		return (new Bill(date,powerCost,waterCost));
		
		
	}
}
