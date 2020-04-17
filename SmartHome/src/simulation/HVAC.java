package simulation;

import java.util.ArrayList;
import java.util.Random;

public class HVAC extends Simulations{
	
	// HVAC uses 3500 Watts per hour
	// we want the house to stay at 
	
	public double hvacCost(double hours) {
		double watts = 3500 * hours;
		double cost = (watts / 1000) * 0.12;
		return cost;
	}
	
	public double hvacHours(ArrayList<Integer> temps, String days) {
		
		//Assuming windows are open for half an hour and have a 0.10 probability of being opened
		Random rand = new Random();
		
		int outsideTemp;
		int doorEvents = 0;
		int doorEventsLimit =0;
		int doorTime = 0;
		int random;
		int tempMultiplier;
		int setTemp = 69; // thermostat set temp
		int currentTemp = 69; //indoor temp
		int hvacMinutes = 0;
		
		if (days.contentEquals("weekend")) {
			doorEventsLimit = 32;
		}
		if (days.contentEquals("weekday")) {
			doorEventsLimit = 64;
		}
		
		for (int hour = 0; hour < 24; hour++) {
			//System.out.println();
			//System.out.println(hour);
			outsideTemp = temps.get(hour);
			random = rand.nextInt(100);
			if (random <=10) { //window is open
				currentTemp += ((outsideTemp - currentTemp)/10) * 6;
				//System.out.println("Window event occurred");
			}
			if (doorEvents < doorEventsLimit) {//door event occurred
				doorTime += 30;
				doorEvents += 1;
				//System.out.println("Door event occurred");
			}
			
			if (doorTime > (60*5)) {
				doorTime = 0;
				currentTemp += (2*((outsideTemp - currentTemp)/10));
			}
			
			//System.out.println(currentTemp);
			while (Math.abs(setTemp - currentTemp) > 2) {
				hvacMinutes += 1;
				///System.out.println("HVAC ON");
				if (currentTemp < setTemp) {
					currentTemp += 1;
				}
				if (currentTemp > setTemp) {
					currentTemp -= 1;
				}
			}
		}
		return ((double) hvacMinutes / 60);
	}		 
}