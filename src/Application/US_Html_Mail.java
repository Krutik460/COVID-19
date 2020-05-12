package Application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class US_Html_Mail {
	
	static int counter = 0;
	
	static DateTimeFormatter date = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
	static LocalDateTime now = LocalDateTime.now();
	
	static String US_HTMLTop = "<html lang=\"en\">\n" + 
			"<head>\n" + 
			"<meta charset=\"UTF-8\">\n" + 
			"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" + 
			"<style>\n" + 
			"\n" + 
			".header {\n" + 
			"  padding: 10px;\n" + 
			"  text-align: center;\n" + 
			"  background: LightBlue;\n" + 
			"  color: white;\n" + 
			"  font-size: 20px;\n" + 
			"}\n" + 
			".footer {\n" + 
			"  padding: 10px;\n" + 
			"  text-align: center;\n" + 
			"  background: LightBlue;\n" + 
			"  color: white;\n" +  
			"}\n" + 
			".content{\n" + 
			//"  font-size: 15px;\n" +
			"  padding: 15px;\n" +
			"  border: 2px solid black;\n" + 
			"}\n" + 
			"\n" + 
			".tabelclass{ "
			+ "  padding: 30px;\n" 
			+ "}"+
			"\n"
			+ ".co-table{\n" + 
			"		padding: 30px;\n" + 
			"		border: 2px solid black;\n" + 
			"		background-color: #E9FFFF;\n" + 
			"		border-collapse: collapse;\n" + 
			"		text-align: center;\n" + 
			"}\n" +
			"</style>\n" + 
			"</head>\n" + 
			"<body>\n" + 
			"\n"+ 
			//"<img src=\"https://www.google-analytics.com/collect?v=1&tid=G-GEB78P2PBD&cid=555&t=event&ec=email&ea=open&dp=%2Femail%2Fnewsletter1&dt=My%20Newsletter\">" + 
			"<div class=\"header\">\n" + 
			"<h1 style=\"color:#404040;\">" + date.format(now) + "</h1>\n" +
			"  <h2 style=\"color:#404040;\">Not all heroes wear capes... but a whole dedicated bunch of them wear scrubs.</h2>\n" + 
			//"   <p><strong>~ Donald Trump </strong></p>" + 
			"</div>" +
			"<body>" +
			"<br>\n" +
			"\n";
	
	static String PA_HTMLBottom = "<br><div class=\"footer\">" + 
			"		<h4 style=\"color:white;font-size: 10px;\">Keep Washing!</p> \n" + 
			"		<p style=\"color:white;font-size: 7px;\">We appriciate you and would like to keep providing reliable information during hard times. <br> Still if you wish to unsubscribe to News Letter, just reply to mail wil a reason. So that we can improve our service</p>\n" + 
			"		</div>" +
			"</body>\n" + 
			"</html>";
	
	static ArrayList<String> newsHtml = new ArrayList<String>();
	
	public static String USTotalHtml () {
		US__Web_Scrapper.USTotalcount();
		String UStotalhtml = "<div class=\"tabelclass\">\n" + 
				"<table style=\" margin: auto; text-align: center;\">\n" + 
				"<tbody>\n" + 
				"<tr class=\"ms-rteTableOddRow-default\">\n" + 
				"<td class=\"ms-rteTableOddCol-default\" style=\"font-size: 35px;\"><strong>" + US__Web_Scrapper.totalCase.get(0) + "</strong></td>\n" + 
				"<td class=\"ms-rteTableEvenCol-default\" style=\"font-size: 35px;\"><strong>" + US__Web_Scrapper.totalCase.get(1) + "</strong></td>\n" + 
				"</tr>\n" + 
				"  <tr class=\"ms-rteTableEvenRow-default\">\n" + 
				"<td class=\"ms-rteTableOddCol-default\" style=\"width: 326.609px;\">Confirmed Cases</td>\n" + 
				"<td class=\"ms-rteTableEvenCol-default\" style=\"width: 326.609px;\" colspan=\"1\">​Deaths</td>\n" + 
				"</tr>\n" + 
				"</tbody>\n" + 
				"</table>\n" + 
				"</div>\n" + 
				"<br>\n" + 
				"<br>";
		return UStotalhtml;
	}
	
	public static String USStateHtml() {
		US__Web_Scrapper.USStateCount();
		return HTMLStringCreator(US__Web_Scrapper.stateCase);
	}
	
	public static String USNewsHtml() {
		US__Web_Scrapper.USNews ();
		
		for (int i = 0; i < US__Web_Scrapper.headline.size(); i = i + 2) {
			newsHtml.add("<div class=\"content\">");
			newsHtml.add(US__Web_Scrapper.headline.get(i));
			//newsHtml.add(US__Web_Scrapper.headline.get(i + 1));
			newsHtml.add("</div><br>\n");
		}
		return HTMLStringCreator(newsHtml);
	}
	
	public static String USHTMLCreator() {
		String totalCase = USTotalHtml ();
		String news = USNewsHtml();
		//String top10 = USStateHtml();
		
		return US_HTMLTop + totalCase + news /**+ top10 **/+ PA_HTMLBottom;
	}
	
	
	
	public static String HTMLStringCreator (ArrayList<String> list) {
		String HTMLcode = String.join("\n", list);
		return HTMLcode;
	}
}
