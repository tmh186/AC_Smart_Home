package connections;

public class WeatherTest {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String data = "{\"coord\":{\"lon\":-86.8,\"lat\":33.52},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02d\"}],\"base\":\"stations\",\"main\":{\"temp\":68.04,\"feels_like\":61,\"temp_min\":64.4,\"temp_max\":72,\"pressure\":1018,\"humidity\":25},\"visibility\":16093,\"wind\":{\"speed\":5.88,\"deg\":152},\"clouds\":{\"all\":20},\"dt\":1586628440,\"sys\":{\"type\":1,\"id\":3436,\"country\":\"US\",\"sunrise\":1586604053,\"sunset\":1586650490},\"timezone\":-18000,\"id\":4049979,\"name\":\"Birmingham\",\"cod\":200}";
		Weather.tempreturn(data);
	}

}
