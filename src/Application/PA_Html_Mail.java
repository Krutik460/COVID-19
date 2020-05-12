package Application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PA_Html_Mail {
	
	static DateTimeFormatter date = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
	static LocalDateTime now = LocalDateTime.now();
	
	static String PA_HTMLTop = "<html lang=\"en\">\n" + 
			"<head>\n" + 
			"<title>Page Title</title>\n" + 
			"<meta charset=\"UTF-8\">\n" + 
			"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" + 
			"<style>\n" + 
			"\n" + 
			"body {\n" + 
			"  font-family: Arial;\n" + 
			"  margin: 0;\n" + 
			"}\n" + 
			"\n" + 
			".header {\n" + 
			"  padding: 10px;\n" + 
			"  text-align: center;\n" + 
			"  background: LightPink;\n" + 
			"  color: white;\n" + 
			"  font-size: 20px;\n" + 
			"}\n" + 
			".footer {\n" + 
			"  padding: 10px;\n" + 
			"  text-align: center;\n" + 
			"  background: LightPink;\n" + 
			"  color: white;\n" + 
			"}\n" + 
			".content{\n" + 
			//"  font-size: 15px;\n" +
			"  padding: 15px;\n" +
			"  border: 2px solid black;\n" + 
			"}\n" + 
			"\n" + 
			"table, th, td{\n" + 
			"  padding: 10px;\n" +
			"  border: 2px solid black;\n" + 
			"  background-color: #FFE9EC;\n" + 
			"  border-collapse: collapse;\n" + 
			"  text-align: center\n" + 
			"}\n" + 
			".tabelclass{padding:20px}"+
			"\n" +
			"</style>\n" + 
			"</head>\n" + 
			"<body>\n" + 
			"\n" + 
			"<div class=\"header\">\n" + 
				"<h1 style=\"color:#404040;\">" + date.format(now) + "</h1>\n" +
			"  <h2 style=\"color:#404040;\">Not all heroes wear capes... but a whole dedicated bunch of them wear scrubs.</h2>\n" + 
			//"   <p>~ Donald Trump</p>" + 
			"</div>" +
			"<body>" +
			"\n" +
			"\n";
	static String PA_HTMLBottom ="<br><div class=\"footer\">" + 
			"		<h4 style=\"color:white;font-size: 10px;\">Keep Washing!</p> \n" + 
			"		<p style=\"color:white; font-size: 7px;\">We appriciate you and would like to keep providing reliable information during hard times. <br> Still if you wish to unsubscribe to News Letter, just reply to mail wil a reason. So that we can improve our service</p>\n" + 
			"		</div>"
			+ "</body>\n" + 
			"</html>";
	
	static String HTMLTabelTop = "<div class=\"tabelclass\">" + 
								 "\n<table style=\"width:80%; margin:auto;\">\n" +
								 "<caption><h2>Total Cases in Pennsylvania</h2><caption>";
	
	static String HTMLTabelBottom = "\n</table>" +
								 	"</div>" +
								 	"<br><br>";
	
	static String HTMLNewsTop = "<div>";
	static String HTMLNewsBottom = "</div>\n"; // Already added before and after news after scraping
	
	public static String HTMLStringCreator (ArrayList<String> list) {
		String HTMLcode = String.join("\n", list);
		return HTMLcode;
	}
	
	
	public static String PAHTMLCreator () {
		//News HTML Code
		String PANewsHTML = (HTMLStringCreator(PA__Web_Scrapper.News));
		
		//Tabel HTML CODE
		String PACasetotalHTML = (HTMLTabelTop + HTMLStringCreator(PA__Web_Scrapper.totalCase) + HTMLTabelBottom);
		String PACasecountyHTML = ("<div class=\"content\">" +
				 "\n<table style=\"width:40%; margin-left:auto; margin-right:auto;\">\n" +
				"<caption><h2>Breakdown By County</h2><caption>"+ HTMLStringCreator(PA__Web_Scrapper.countycase) + 
				"<caption><h3> <a href=" + PA__Web_Scrapper.PAMapurl + ">View In Map</a> </h3><caption>" +
				"\n</table>"+ "<br><br>" + "</div>");
		
		return PA_HTMLTop + PACasetotalHTML + PANewsHTML + PACasecountyHTML + PA_HTMLBottom;
	}
	
	
	public static String SubEmail () {
		String thankYou = "<!DOCTYPE html>\n" + 
				"<html lang=\"en\">\n" + 
				"<head>\n" + 
				"<title>Page Title</title>\n" + 
				"<meta charset=\"UTF-8\">\n" + 
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" + 
				"<style>\n" + 
				"/* Style the body */\n" + 
				"body {\n" + 
				"  font-family: Arial;\n" + 
				"  margin: 0;\n" + 
				"}\n" + 
				"\n" + 
				"/* Header/Logo Title */\n" + 
				".header {\n" + 
				"  padding: 20px;\n" + 
				"  text-align: center;\n" + 
				"  background: #1abc9c;\n" + 
				"  color: white;\n" + 
				"  font-size: 20px;\n" + 
				"}\n" + 
				"\n" + 
				"p {\n" + 
				"  line-height: 1.8;\n" + 
				"}\n" + 
				".content {padding:20px;}\n" + 
				"</style>\n" + 
				"</head>\n" + 
				"<body>\n" + 
				"\n" + 
				"<div class=\"header\">\n" + 
				"  <h1>Thank You</h1>\n" + 
				"  <p>Stay Home, Stay Safe</p>\n" + 
				"</div>\n" + 
				"\n" + 
				"<div class=\"content\">\n" + 
				"  <h2>Hello there,</h2>\n" + 
				"  <p> Thanks for signing up to receive an update about COVID-19. We promise you to keep you up to date with information regarding Coronavirus. We only gather information through the official websites and pack that into .\n" + 
				"  <br> We hope you love and appreciate the information. Stay home, Stay safe and everything will be okay before you know!\n" + 
				"  <br>\n" + 
				"  <br>\n" + 
				"  Thank You\n" + 
				"  </p>\n" + 
				"</div>\n" + 
				"\n" + 
				"</body>\n" + 
				"</html>\n" + 
				"\n";
		
		return thankYou;
	}
}
