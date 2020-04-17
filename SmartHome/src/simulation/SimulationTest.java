package simulation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SimulationTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Simulations s = new Simulations();
		
		
		double weekend = s.waterCost("weekend");
		double weekday = s.waterCost("weekday");
		
		System.out.println("Water cost on Weekends: ");
		System.out.println(weekend);
		System.out.println("Water cost on Weekdays: ");
		System.out.println(weekday);
		
		System.out.println();
		
		System.out.println("Water watts on weekends: ");
		System.out.println(s.waterWatts("weekend"));
		System.out.println("Water watts on weekdays: ");
		System.out.println(s.waterWatts("weekday"));
		
		System.out.println();
		
		System.out.println("Cost of fixed power expenses including water heating on weekends: ");
		System.out.println(s.powerCostFixed("weekend"));
		System.out.println("Cost of fixed power expenses including water heating on weekdays: ");
		System.out.println(s.powerCostFixed("weekday"));
		
		System.out.println();
		System.out.println("The following is the full list of events for a day, weekend and then weekday");
		System.out.println(Arrays.asList(s.randomPowerEvents("weekend")));
		System.out.println(Arrays.asList(s.randomPowerEvents("weekday")));
		
		System.out.println();
		System.out.println("Cost of random power events on weekends, then weekdays ");
		System.out.println(s.calculateRandomPowerCost(s.randomPowerEvents("weekend")));
		System.out.println(s.calculateRandomPowerCost(s.randomPowerEvents("weekday")));
		
		System.out.println();
		System.out.println("Simulating a day of HVAC operation:");
		HVAC h = new HVAC();
		
		ArrayList<Integer> temps = new ArrayList<Integer>();
		Random rand = new Random();
		for (int i = 0; i < 24; i++) {
			temps.add(ThreadLocalRandom.current().nextInt(80, 90 + 1));
			//generating test data
		}
		System.out.println(h.hvacHours(temps, "weekday"));
		
		
		
		
	}

}
