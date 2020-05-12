package Application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class PA__Web_Scrapper {
	
	static String PACountyurl = "https://www.health.pa.gov/topics/disease/coronavirus/Pages/Cases.aspx";
	static String PANewsurl = "https://pacast.com";
	static String PAMapurl = "https://www.google.com/maps/d/u/0/embed?mid=1lpLPPrltIuVkwFtXhoWdWEA4B1DvNmne&ll=41.03302245465488%2C-77.80500120579285&z=7";
	
	static ArrayList<String> totalCase = new ArrayList<String>();
	static ArrayList<String> countycase = new ArrayList<String>();
	//not using for now
	static ArrayList<String> ageBreakDown = new ArrayList<String>();
	
	static ArrayList<String> News = new ArrayList<String>();
	
	static String updatedTime;
	static boolean updateNeeded = false;
	
	
	/**
	 * This method will output true or Fase if there is update on website
	 * @return True - Update Needed
	 * @return False - No Update
	 */
	public static boolean PAStatsCheck() {
		
		
		try {
			Document PACounty = Jsoup.connect(PACountyurl).userAgent("Chrome").get();
			
			updatedTime = PACounty.getElementsByClass("ms-rteStyle-Quote").text();
			ArrayList<String> updatedTimeArray = new ArrayList<String>(Arrays.asList(updatedTime.split(" ")));
			

			BufferedReader reader = new BufferedReader(new FileReader("PAUpdate.txt"));
			ArrayList<String> timeArray = new ArrayList<String>(Arrays.asList(reader.readLine().split(" ")));
			
			if (updatedTimeArray.equals(timeArray)) {
				updateNeeded = false;
			}
			else {
				File PALatestUpdate = new File("PAUpdate.txt");
				FileWriter Writer = new FileWriter(PALatestUpdate);
				Writer.write(updatedTime);
				Writer.close();
				updateNeeded = true;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return updateNeeded;
	}
	
	
	/**
	 * This method will scrape through website to get the stats about the case in PA and store the HTML of Tabel in ArrayList.
	 */
	public static void PACaseStats() {
		 try {
				Document PACounty = Jsoup.connect(PACountyurl).userAgent("Chrome").get();
				
				Elements rows = PACounty.getElementsByClass("ms-rteTable-default").select("tr");
				
				for (int i = 0; i < rows.size(); i ++) {
					if (i < 2) {
						totalCase.add(rows.get(i).outerHtml() + "\n");
					}
					else if (i < 16 ){
						ageBreakDown.add(rows.get(i).outerHtml() + "\n");
					}
					else if (i < 84){
						countycase.add(rows.get(i).outerHtml() + "\n");
					}
					
				}
				
				//OLD CODE THAT STORES DATA IN LIST
				/**
				Elements rows = PACounty.getElementsByClass("ms-rteTable-default").select("tr");
				
				for (Element row : rows) {

					if (row.getElementsByTag("td").first() == null) {
						continue;
					}
					else {
						Elements tds = row.getElementsByTag("td");
						
						ArrayList<String> data = new ArrayList<String>(Arrays.asList(tds.text().split(" ")));
						if (data.size() == 2 & counter > 1) {
							data.add("0");
							countyCase.add(data);
						}
						else if (counter > 1) {
							countyCase.add(data);
						}
						else {
							totalCase.add(data);
						}
					} 
					counter = counter + 1;
				}**/
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	 }
	
	
	/**
	 * This will screape the all the news along with date and link to that news. 
	 */
	public static void PANews() {
		try {
			Document PANewsdoc = Jsoup.connect(PANewsurl).userAgent("Chrome").get();
			
			
			Elements headlines = PANewsdoc.getElementsByClass("content").select("header");
			Elements viewmedia = PANewsdoc.getElementsByClass("content").select("a");
			
			String shorturl = null;
			
			for (int i = 1; i < 6; i = i + 1) {
				News.add("<div class=\"content\">");
				News.add(headlines.get(i).outerHtml());
				shorturl = viewmedia.get(i).outerHtml().substring(9, 19);
				News.add("<a href=\""+ PANewsurl + "/" + shorturl + " class=\"button2\">View Media</a>");
				News.add("</div>\n"
						+ "<br>");
			}
			
			/**
			for (Element headline : headlines) {
				ArrayList<String> NewsHeadlines = new ArrayList<String>(Arrays.asList(headline.text().split(":")));
				News.add(NewsHeadlines);
				
			}**/
			
			/**
			Elements urls = PANewsdoc.getElementsByClass("content").select("ul").select("a[href]");
			for (Element url: urls) {
				News.get(counter).add((PANewsurl + "/" + url.attr("href")).trim());
				System.out.println(News.get(counter));
				counter = counter + 1;
			}**/
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}