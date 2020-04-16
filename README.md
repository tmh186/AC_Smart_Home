# Smart Home IoT

## Collaboration:
Cole O'Brian, Robert Byers, Samuel Casey, Trey Hogan, Cullen Steber, Pratyusha Thundena 

### Objective:
Created a virtual smart home for a family of four (i.e. mom,
dad, 2 kids). There were two parts of this project: 1) Sensor Data (i.e.
Data Randomization) and 2) User Interaction. Project requirements included:

1a. House Details
 * 	2 adults, 2 kids
 * 3 Bed (overhead light, two lamps, 1 TV in master bedroom)
 *	2 Bath (overhead light, exhaust fan)
 *	2 car attached garage (two garage doors)
 *	Clothes washer and dryer
 *	3 exterior doors (front, back, house into garage)
 *	Living room (overhead light, two lamps, TV)
 *	Kitchen (overhead light, stove, oven, microwave, refrigerator, dishwasher)

 1b. IoT Sensor Data Sources:
 *	Camera (Optional)
 *	Door sensor (exterior doors and garage doors)
 *	Window sensors
 *	Motion sensor (Optional)
 *	Beacon tags (pets, keys, purse, etc.) (Optional)
 *	Water flow (kitchen, bathrooms (sink/shower), outside faucet)
 *	Lights on/off
 *	HVAC (external temp, internal temp, thermostat high/low settings)
 *	External temp history and real time readings from online source

2. Formulas and Constants
Defined the mathematical implementation of the formulas using the following info: 
*	HVAC Operation - +/- 1 deg F per minute of operation
*	HVAC will maintain the set temp within 2 degrees (i.e. if the inside temp goes beyond 2 degrees of the set temp, then it will start operation to bring the temp back to the set temp).
*	Interior Temp Change
*	House Closed – For every 10 deg F difference in external temp, interior tem will +/- 2 deg F per hour 
*	Open Door – For every 10 deg F difference in external temp, interior tem will +/- 2 deg F per 5 min door is open
*	Open Window - For every 10 deg F difference in external temp, interior tem will +/- 1 deg F per 5 min window is open
*	Door Opening- Exterior door is open 30 seconds each time a person enters or leaves the house,M – F : 16 exit/enter events per day, S – S : 32 exit/enter events per day
*	Electricity Cost - $0.12 per kWh ( 1w = 1/1000 kw)
*	Water Cost – $2.52 per 100 Cubic Feet of water,1 Cubic Feet of water is 7.48 Gallons,	100 Cubic Feet is 748 Gallons, So 748 Gallons costs $2.52
*	All Light Bulbs are 60w
*	Bath exhaust fan – 30w
*	HVAC – 3500w
*	Refrigerator – 150w
*	Microwave -	1100w,	M – F : 20 min/day,S – S : 30 min/day
* Hot Water Heater- 4500w, 4 minutes to heat 1 gallon of water
*Stove -	3500 watts,	M – F : 15 min/day,	S – S : 30 min/day
*	Oven - 4000 watts,	M – F : 45 min/day,	S – S : 60 min/day
*	TV -	Living Room TV, 636 watts, M – F : 4 hrs/day, S – S : 8 hrs/day
*Bedroom TV - 100 watts,	M – F : 2 hrs/day, S – S : 4 hrs/day
*	Baths - M – F : 2 showers and 2 baths per day, S – S : 3 showers and 3 baths per day
*	Shower – 25 gallons of water used (65% hot water, 35% cold water)
*	Bath – 30 gallons of water used (65% hot water, 35% cold water)
*	Dishwasher - 1800 watts, 6 gallons of hot water per load,	Runs 45 min per load, 4 loads of dishes per week
*	Clothes Washer - 500 watts, 20 gallons of water (85% hot water, 15% cold water) per load, Runs 30 min per load,	4 loads of clothes per week
*	Clothes Dryer - 3000 watts, Runs 30 min per load, 4 loads of clothes per week
*	Adults wake at 5AM, go to bed at 10:30PM, 	Kids wake at 6AM, go to bed at 8:30PM, Adults leave for work at 7:30AM, return home at 5:30PM, Kids leave for school at 7:30AM, return home at 4PM

3. User Input/System Rsponse Requirements:
*	Menu selection
*	Sensor manipulation
*	Open and close doors
*	Turn lights and TV on/off
*	Set thermostat
*	Typed text
*	Screen graphics
*	Screen text
* Utility usage and cost calculations
* Historical and predicted for current month.

### Technologies:

 https://jdbc.postgresql.org/download.html, https://www.eclipse.org/downloads/, https://www.pgadmin.org/download/, https://gluonhq.com/products/javafx/, https://www.uab.edu/it/home/vpn