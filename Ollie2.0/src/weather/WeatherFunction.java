package weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class WeatherFunction {
	
	public static ArrayList<String> getWeather(String location) {
		
		ArrayList<String> info = new ArrayList<String>();

		Weather weather = locationForWeather(location);

		info.add(weather.weather.get(0).description.toUpperCase() + "\n");
		
		info.add("Current temperature: " + 
				kelvinToCelcius(weather.main.temp) + " °C (" +
				kelvinToFahrenheit(weather.main.temp) + " °F) \n");
		
		info.add("The pressure: " + 
				weather.main.pressure + " hPa \n");
		
		info.add("The humidity: " + 
				weather.main.humidity + " percent \n");
		
		info.add("The maximum temperature: " + 
				kelvinToCelcius(weather.main.temp_max) + " °C (" +
				kelvinToFahrenheit(weather.main.temp_max) + " °F) \n");
		
		info.add("The minimum temperature: " + 
				kelvinToCelcius(weather.main.temp_min) + " °C (" +
				kelvinToFahrenheit(weather.main.temp_min) + " °F) \n");
		
		info.add("The wind speed: " + 
				weather.wind.speed + " kph \n");
		
		return info;
	}

	public static Weather locationForWeather(String location) {
		location = location.replaceAll("\\s+", "");
		String APIKey = "50304844df23acee48676e4583aaf195";
		String url = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + APIKey;

		String json = getStringFromURL(url);
		Gson gson = new Gson();
		Weather weather = gson.fromJson(json, Weather.class);

		return weather;
	}

	public static String getStringFromURL(String myURL) {
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:" + myURL, e);
		}

		return sb.toString();
	}

	public static double kelvinToCelcius(double kel) {
		return round2Digits(kel - 273);
	}

	public static double kelvinToFahrenheit(double kel) {
		return round2Digits((9 / 5) * (kel - 273) + 32);
	}
	
	public static double round2Digits(double num) {
		return Math.round(num * 100.0) / 100.0;
	}
}

class Weather {
	List<WeatherJson> weather;
	MainJson main;
	WindJson wind;
}

class WeatherJson {
	String description;
}

class MainJson {
	double temp;
	double pressure;
	double humidity;
	double temp_min;
	double temp_max;
}

class WindJson {
	double speed;
}
