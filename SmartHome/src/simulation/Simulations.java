package simulation;
import java.util.ArrayList;
import java.util.Random;

import connections.Device;

public class Simulations {
		
		ArrayList<Device> devices = new ArrayList<Device>();
		
		Device d1 = new Device("Master", "light", false);
		Device d2 = new Device("Master", "lamp1", false);
		Device d3 = new Device("Master", "lamp2", false);
		Device d4 = new Device("BR1", "light", false);
		Device d5 = new Device("BR1", "lamp1", false);
		Device d6 = new Device("BR1", "lamp2", false);
		Device d7 = new Device("BR2", "light", false);
		Device d8 = new Device("BR2", "lamp1", false);
		Device d9 = new Device("BR2", "lamp2", false);
		Device d10 = new Device("MasterBath", "light", false);
		Device d11 = new Device("MasterBath", "fan", false);
		Device d12 = new Device("Bath", "light", false);
		Device d13 = new Device("Bath", "fan", false);
		Device d14 = new Device("Den", "light", false);
		Device d15 = new Device("Den", "lamp1", false);
		Device d16 = new Device("Den", "lamp2", false);
		Device d17 = new Device("Kitchen", "light", false);
	
		// TODO Auto-generated constructor stub
		
		// Adults up at 5am, bed at 10:30pm
		// Kids up at 6am, bed at 8:30pm
		// Adults work weekdays at 7:30am to 5:30pm
		// Kids have school weekdays at 7:30am to 4:00pm
		
		// Dead time -- 10:30pm - 5:00 am
		// Dead time -- weekdays 7:30am - 4:00pm
		
		// Everyone up and moving -- Weekends 7:00am to 11:00pm
		// Morning everyone showers, brushes teeth, eats breakfast (cold) 
	
	public ArrayList<Event> everythingOff(double hour){
		ArrayList<Event> e = new ArrayList<Event>();
		
		for (int i = 0; i < this.devices.size(); i++) {
			if (this.devices.get(i).isOff()) {
				continue;
			}
			else {
				e.add(new Event(this.devices.get(i), hour, false));
				this.devices.get(i).changeState();
			}
		}
		
		return e;
	}
	public ArrayList<Event> randomPowerCosts(String days) {
		
		// Basic idea here:
		// Create device for all random variable things
		// Randomly decide an event, on/off, room, and device
		// Add event to an array of events 
		// Events only occur when people are home and up. 
		// We can turn everything off with the helper function everythingOff
		
		ArrayList<Event> e = new ArrayList<Event>();
		this.devices.add(d1);
		this.devices.add(d2);
		this.devices.add(d3);
		this.devices.add(d4);
		this.devices.add(d5);
		this.devices.add(d6);
		this.devices.add(d7);
		this.devices.add(d8);
		this.devices.add(d9);
		this.devices.add(d10);
		this.devices.add(d11);
		this.devices.add(d12);
		this.devices.add(d13);
		this.devices.add(d14);
		this.devices.add(d15);
		this.devices.add(d16);
		this.devices.add(d17);
		
		double hour;
		int multiplier = 4;
		int num;
		int index;
		Random rand = new Random();
		
		for (hour = 0.0; hour < 24.00; hour += 0.05) {
			if (days.contentEquals("weekend")) {
				if (hour < 5.00 || hour > 22.50) {
					continue;
				}
				if (hour == 22.50) {
					e.addAll(everythingOff(hour));
					continue;
				}
				else {
					multiplier = 4;
					num = rand.nextInt(100);
					if (num < (15*multiplier)) { //15/100 times the number of people in the house determine the event
						index = rand.nextInt(17);
						this.devices.get(index).changeState();
						e.add(new Event(this.devices.get(index), hour, this.devices.get(index).isState()));
						
					}
					else {
						continue;
					}
					
				}
			}
			
			
			if (days.contentEquals("weekday")) {
				if (hour < 5.00 || hour > 22.50) {
					continue;
				}
				if (hour > 5.00 && hour <6.00) {
					multiplier = 2;
					num = rand.nextInt(100);
					if (num < (15*multiplier)) { //15/100 times the number of people in the house determine the event
						index = rand.nextInt(17);
						this.devices.get(index).changeState();
						e.add(new Event(this.devices.get(index), hour, this.devices.get(index).isState()));
					}
					else {continue;}
				}
				if (hour > 6.00 && hour > 7.50) {
					multiplier = 4;
					num = rand.nextInt(100);
					if (num < (15*multiplier)) { //15/100 times the number of people in the house determine the event
						index = rand.nextInt(17);
						this.devices.get(index).changeState();
						e.add(new Event(this.devices.get(index), hour, this.devices.get(index).isState()));
					}
				}else {continue;}
			
					
				if (hour == 7.50) {
					e.addAll(everythingOff(hour));
				}
				if (hour > 7.50 && hour < 16.00) {
					continue;
				}
				if (hour > 16.00 && hour < 17.50) {
					multiplier = 2;
					num = rand.nextInt(100);
					if (num < (15*multiplier)) { //15/100 times the number of people in the house determine the event
						index = rand.nextInt(17);
						this.devices.get(index).changeState();
						e.add(new Event(this.devices.get(index), hour, this.devices.get(index).isState()));
					}
					else {continue;}
				}
				if (hour > 17.50 && hour < 20.50) {
					multiplier = 4;
					num = rand.nextInt(100);
					if (num < (15*multiplier)) { //15/100 times the number of people in the house determine the event
						index = rand.nextInt(17);
						this.devices.get(index).changeState();
						e.add(new Event(this.devices.get(index), hour, this.devices.get(index).isState()));
					}else {continue;}
				}
				if (hour > 20.50 && hour < 22.50) {
					multiplier = 2;
					num = rand.nextInt(100);
					if (num < (15*multiplier)) { //15/100 times the number of people in the house determine the event
						index = rand.nextInt(17);
						this.devices.get(index).changeState();
						e.add(new Event(this.devices.get(index), hour, this.devices.get(index).isState()));
					}else {continue;}
				}
				
				if (hour == 22.50) {
					e.addAll(everythingOff(hour));
					}
					
				else {
					continue;
				}
			}
		}
		
		return e;

	}
	
	
	
		
	
	
	public int waterWatts(String days) {
		// hot water heater takes 4500w/hr
		// 4 mins per 1 gal water 
		// cold gallons don't require heating (duh) 
		// we need to determine how many gallons are HEATED per day 
		double hotGallons = 0;
		int watts;
		double time;
		
		if (days.contentEquals("weekend")) {
			hotGallons += 3*(25*0.65); //showers
			hotGallons += 3*(30*0.65); //baths
			hotGallons += 12; //dishwasher (2 loads per weekend day)
			hotGallons += 2*(20*0.85); // clothes washer (2 loads per weekend day)
			
			time = (4*hotGallons)/60; //this is in hours
			
			watts = (int)time * 4500;
			
			return watts;
			
		}
		
		if (days.contentEquals("weekday")) {
			hotGallons += 2*(25*0.65); //showers
			hotGallons += 2*(30*0.65); //baths
			
			time = (4*hotGallons)/60; //this is in hours
			
			watts = (int)time * 4500;
			
			return watts;
		}
		
		return 0;
		
		
		
	}
	
	public double powerCostFixed(String days) {
		//Some assumptions
		//Fridge is always on - dishes and laundry are done on weekends for simplicity (2 per day) 
		//This function is only for fixed and known costs (i.e no hvac)
		//waterWatts is a helper function to determine the cost of heating water for each day, eliminating a step
		double cost;
		int watts = 0;
		
		if (days.contentEquals("weekend")) {
			watts += (150*24); //fridge for all 24 hours of the day
			watts += (1100*0.5); //microwave usage 
			watts += (3500*0.5); //stove usage 
			watts += 4000; //Oven usage 
			watts += (636*8); //Living room TV
			watts += (100*4); //Bedroom TV
			watts += (1800 * 1.5); //2 loads of dishes
			watts += (500); //2 loads of laundry (washer)
			watts += (3000); //2 loads of laundry (dryer)
			watts += waterWatts(days);
			
			cost = (watts/1000) * 0.12;
			
			return cost;
				
		}
		
		if (days.contentEquals("weekday")) {
			watts += (150*24); //fridge for all 24 hours of the day
			watts += (1100*(1/3)); //microwave usage 
			watts += (3500*0.25); //stove usage 
			watts += (4000*0.75); //Oven usage 
			watts += (636*4); //Living room TV
			watts += (100*2); //Bedroom TV
			watts += waterWatts(days);
			
			cost = (watts/1000) * 0.12;
			
			return cost;
		}
		
		return 0;
	}
	
	
		
	public double waterCost(String days) { // this is JUST for water costs and power related to water items will
		//be calculated in another function
		double cost;
		int gallons = 0;
		if (days.contentEquals("weekend")) {
			//2 loads of laundry per weekend day so all laundry is on weekends
			//same for dishes
			gallons += (25*3); //3 showers
			gallons += (30*3); //3 baths
			gallons += (6*2); //2 loads of dishes
			gallons += (20*2); //2 loads of laundry 
			
			cost = (2.52/748)*(gallons);
			
			return cost;
		}
		
		if (days.contentEquals("weekday")) {
			gallons += (23*2); //2 showers
			gallons += (30*2); //2 baths
			
			cost = (2.52/748)*(gallons);
			
			return cost;
		}
		
		else {
			return 0.00;
		}
	}
		
		
	


}
