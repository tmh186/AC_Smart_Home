package simulation;

import java.util.Arrays;

public class SimulationTest {

	public static void main(String[] args) {
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
		System.out.println(Arrays.asList(s.randomPowerCosts("weekend")));
		System.out.println(Arrays.asList(s.randomPowerCosts("weekday")));
		
		
		
	}

}
