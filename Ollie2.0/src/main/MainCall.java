package main;

import java.util.ArrayList;
import java.util.Random;

public class MainCall {
	public static String[] calculatorWords = { "Sum", "Add", "Plus", "Total", "Subtract", "Calculate", "Equal", "Math",
			"-", "+", "*", "/", "Decrease", "Deduct", "Multiply", "Product", "Division", "Divide",
			"Greatest Common Denominator", "GCD", "LCD", "Lowest Common Denominator", "The average of", "Power",
			"Exponent", "Raised to", "Calculation", "Calculator" };
	public static String[] weatherWords = { "Climate", "Temperature", "Celcius", "Farenheit", "Rainy", "Rain", "Clouds",
			"Cloudy", "Sun", "Sunny", "Sunblock", "Wind", "Weather", "Precipitation", "Pressure", "Fog", "Foggy",
			"Snow", "Wind", "Windy", "Humidity", "Humid", "Rainfall", "Blizzard", "Atmosphere", "Winter", "Summer",
			"Fall" };
	public static String[] calendarWords = { "Date", "Day", "Decade", "Annual", "Age", "Minute", "Second", "Year",
			"Time", "Month", "Calendar", "Schedule", "Daytime", "Night Time", "Afternoon", "Monday", "Tuesday",
			"Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "2018" };

	public static String[] howAreYouWords = { "How are you", "How are you doing", "Are you well" };
	public static String[] jokesWords = { "Tell me a joke", "Say a joke", "What's a good joke" };
	public static String[] whereAreYouFromWords = { "Where are you from", "Where were you born",
			"Where did you grow up", "Where do you live", "Where is your home", "Where do you come from",
			"Do you have a house" };
	public static String[] helloWords = { "Hello", "Hey", "Greetings", "What’s up", "Sup", "Bonjour", "Hola", "Namaste",
			"How do you do", "Nice to meet you" };
	public static String[] functionInfoWords = { "What do you do", "What can you do", "What are your functions" };

	public static ArrayList<String> main(String input) {
		ArrayList<String> output = new ArrayList<String>();
		
		if (checkInput(helloWords, input) || input.equalsIgnoreCase("Hi")) {
			output.add("Hey, you! Thanks for the greetings!");
		} else if (checkInput(howAreYouWords, input)) {
			output.add("I am great! Just living the owl life ya feel? Thanks for asking!");
		} else if (checkInput(whereAreYouFromWords, input)) {
			output.add("I was born in Farber Lib, but I now live EVERYWHERE");
		} else if (checkInput(jokesWords, input)) {
			output = jokeGenerator();
		} else if (checkInput(functionInfoWords, input)) {
			//TODO: FINISH WRITING THIS
			output.add("");
		} else if (checkInput(weatherWords, input)) {
			//TODO: Think of a better way!
			output.add("INVOKE WEATHER FUNCTION");
		} else if (checkInput(calendarWords, input)) {
			output.add("INVOKE CALENDAR FUNCTION");
		} else if (checkInput(calculatorWords, input)) {
			output.add("INVOKE CALCULATOR FUNCTION");
		} else {
			output.add("Here are the search results for \"" + input + "\" :");
			output.addAll(search.SearchFunction.search(input));
		}
		
		return output;
	}

	private static boolean checkInput(String[] list, String line) {
		for (int i = 0; i < list.length; i++) {
			if (line.contains(list[i]) || line.contains(list[i].toLowerCase())
					|| line.contains(list[i].toUpperCase())) {
				return true;
			}
		}
		return false;
	}
	
	private static final int NUM_JOKES = 3;
	
	private static ArrayList<String> jokeGenerator() {
		ArrayList<String> getJoke = new ArrayList<String>();
		
		Random rand = new Random();
		int choose = rand.nextInt(NUM_JOKES);
		
		switch (choose) {
		case 0: 
			getJoke.add("Did you hear the one about the little mountain?");
			getJoke.add("It's hill-arious!");
			break;
		case 1:
			getJoke.add("Why do shoemakers go to heaven?");
			getJoke.add("Because they have good soles.");
			break;
		case 2: 
			getJoke.add("What did one plate say to the other?");
			getJoke.add("Lunch is on me.");
			break;
		default:
			getJoke.add("No Jokes Available!");
		}
		
		return getJoke;
	}

}
