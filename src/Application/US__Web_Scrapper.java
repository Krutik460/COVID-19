package Application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.lang.model.element.Element;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;


public class US__Web_Scrapper {
	
	static String USTotalCaseurl = "https://www.worldometers.info/coronavirus/country/us/";
	static String USCaseurl = "https://www.theguardian.com/world/ng-interactive/2020/apr/20/coronavirus-map-of-the-us-latest-cases-state-by-state" ;
	static String USNews = "https://www.nytimes.com/news-event/coronavirus?action=click&pgtype=Article&state=default&module=styln-coronavirus&variant=show&region=TOP_BANNER&context=storyline_menu";
	
	static ArrayList<Integer> totalCase = new ArrayList<Integer>();
	static ArrayList<String> stateCase = new ArrayList<String>();
	static ArrayList<String> headline = new ArrayList<String>();
	
	static String USupdatedTime;
	static boolean USupdateNeeded = false;

	
	public static boolean USStatsCheck() {

		try {
			Document PACounty = Jsoup.connect(USCaseurl).userAgent("Chrome").get();
			
			USupdatedTime = PACounty.getElementsByClass("css-wcxsge").select("time").text();
			ArrayList<String> updatedTimeArray = new ArrayList<String>(Arrays.asList(USupdatedTime.split(" ")));
			

			BufferedReader reader = new BufferedReader(new FileReader("USUpdate.txt"));
			ArrayList<String> timeArray = new ArrayList<String>(Arrays.asList(reader.readLine().split(" ")));
			
			if (updatedTimeArray.equals(timeArray)) {
				USupdateNeeded = false;
			}
			else {
				File PALatestUpdate = new File("USUpdate.txt");
				FileWriter Writer = new FileWriter(PALatestUpdate);
				Writer.write(USupdatedTime);
				Writer.close();
				USupdateNeeded = true;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return USupdateNeeded;
	}
	
	public static void USTotalcount() {
		try {
			
			Document USTotalCase = Jsoup.connect(USTotalCaseurl).userAgent("Chrome").get();
			
			totalCase.add(Integer.parseInt(USTotalCase.getElementsByClass("maincounter-number").get(0).text().replaceAll(",", "")));
			totalCase.add(Integer.parseInt(USTotalCase.getElementsByClass("maincounter-number").get(1).text().replaceAll(",", "")));
			
			/**
			ArrayList<String> USCaseCase = new ArrayList<String>(Arrays.asList(USCase.getElementsByClass("g-body ").get(0).text().replaceAll("\\D+", " ").split(" ")));
			for (int i = 0; i < USCaseCase.size(); i++) {
				
				if (i == 0) {
					totalCase.add(Integer.parseInt((USCaseCase.get(1) + USCaseCase.get(2)).trim()));
				}
				else if (i == 3) {
					totalCase.add(Integer.parseInt(USCaseCase.get(3).trim()));
				}
			}**/

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void USStateCount() {
		try {
			Document USStateCase = Jsoup.connect(USCaseurl).userAgent("Chrome").get();
			
			Elements rows = USStateCase.getElementsByClass("co-table").select("tr");
			
			String USTotaltabeltop = "<br>\n"+ 
					 "<div class=\"tabelclass\">" +
					 "\n<table style=\"width:100%; margin:auto; padding: 50px;\">\n" +
					 "<caption><h2>Top 10 States with Positive Cases</h2><caption>";
			stateCase.add(USTotaltabeltop);
			
			for (int i = 0; i < 9; i ++) {
				stateCase.add(rows.get(i).outerHtml() + "\n");
			}
			stateCase.add("</table>\n" +
						  "<br>");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void USNews () {
		try {
			Document USNewsdoc = Jsoup.connect(USNews).userAgent("Chrome").get();
			
			Elements headlines = USNewsdoc.getElementsByClass("css-1g3a8xd");
			Elements viewmedia = USNewsdoc.getElementsByClass("css-1g3a8xd").select("a");
			
			for (int i = 0; i < headlines.size(); i ++) {
				headline.add("<h2>"+headlines.get(i).text() + "</h2>");
				
				String shorturl = viewmedia.get(i).attr("abs:href");
				headline.add("<a href=\"" + shorturl + "\" class=\"button2\">View Media</a>");
				
				//System.out.println(headline.get(i));
			}
				
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

	