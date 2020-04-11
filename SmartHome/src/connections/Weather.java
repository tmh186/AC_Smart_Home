package connections;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Weather {

	private static String APIURL = "https://api.openweathermap.org/data/2.5/weather?q=birmingham,alabama&appid=899a60d6f5915d3b1e249b880a77b649&units=imperial";
	private static String tempPattern = "temp\":(\\d+.\\d+)";
	
	public Weather() {
		// TODO Auto-generated constructor stub
	}
	
	static double tempreturn(String data) {
		Pattern r = Pattern.compile(tempPattern);
	    // Now create matcher object.
	    Matcher m = r.matcher(data);
		if (m.find()) {
			return Double.parseDouble(m.group(1));
		}
		return 0;
		
	}

	public static double getCurrentWeather() {
		String inline = "";
		try {
			URL url = new URL(APIURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			int respcode = conn.getResponseCode();
			if (respcode != 200) {
				throw new RuntimeException("HttpResponseCode: " + respcode);
			} else {
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				//System.out.println("JSON data:\n");
				//System.out.println(inline);
				sc.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempreturn(inline);
	}

}
