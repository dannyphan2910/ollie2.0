package search;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SearchFunction {

	public static ArrayList<String> search(String searchText) {
		ArrayList<String> links = new ArrayList<String>();
		String encoding = "UTF-8";

		try {
			Document google = Jsoup
					.connect("https://www.google.com/search?q=" + URLEncoder.encode(searchText, encoding))
					.userAgent("Mozilla/5.0").get();

			Elements webLinks = google.getElementsByTag("cite");
			// Check if any results found
			if (!(webLinks.isEmpty())) {

				for (int i = 0; i < 5; i++) {
					if (webLinks.get(i).text().contains("https://")) {
						links.add(webLinks.get(i).text());
					}
				}
			} else {
				return null;
			}

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return links;

	}
}
