package Application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Project_Manager {
	static File eventLog = new File("eventLog.txt");
	static File mailLog = new File("mailLog.txt");
	
	static DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	static LocalDateTime now = LocalDateTime.now();
	
	
	
	public static void main(String[] args ) {
		//NEVER REMOVE THIS
		User_Managment.dataImport();
		
		
		/**To Check for PA Update, Run this**/
		//PA();
		
		/**To Check for US Update, Run this**/
		//US();
		
		
		/**TO TEST MAIL FOR PA**/
		//PA__Web_Scrapper.PACaseStats();
		//PA__Web_Scrapper.PANews();
		//System.out.println(PA_Html_Mail.PAHTMLCreator());
		//mailTest(PA_Html_Mail.PAHTMLCreator(), "krutikpatel.patel@gmail.com", "Pennsylvania");
		
		/**TO TEST MIL FOR US**/
		//System.out.println(US_Html_Mail.USHTMLCreator());
		//mailTest(US_Html_Mail.USHTMLCreator(), "krutikpatel.patel@gmail.com", "United States");
		
		/**TO CREATE USER RUN THIS**/
		//CreateUser("Rohan Gupta".trim().toLowerCase(), "rohangupta2036@gmail.com".toLowerCase(), "Pennsylvania".trim().toLowerCase());
		//User_Managment.userSub("Rohan Gupta".trim().toLowerCase(), "United States".trim().toLowerCase());
		
		/**TO CREATE STATE**/
		//User_Managment.addState("UnitedStates");
		
		/**TO ADD SUBS**/
		//User_Managment.userSub("Krutik Patel".trim().toLowerCase(), "UnitedStates");
		
		
		
		//NEVER REMOVE THIS
		User_Managment.dataUpdate();
		totalMailCounter.main(null);
		System.out.println("Done");
	}
	
	
	public static void PA () {
		String state = "Pennsylvania";
		
		
		if (PA__Web_Scrapper.PAStatsCheck()) {
			eventlog(date.format(now) + " - PA DATA CHECK (Update Needed)");
			
			System.out.println("PA Updated");
			PA__Web_Scrapper.PACaseStats();
			PA__Web_Scrapper.PANews();
			String HTMLcode = PA_Html_Mail.PAHTMLCreator();
			
			
			for (int i = 0; i < User_Managment.state.get(state.replaceAll(" ", "").toLowerCase()).size(); i++) {
				//System.out.println(User_Managment.state.get(state).get(i).trim());
				//System.out.println(User_Managment.user.get(User_Managment.state.get(state).get(i).trim()));
				Mail_Sender.sendMail(HTMLcode, User_Managment.user.get(User_Managment.state.get(state.replaceAll(" ", "").toLowerCase()).get(i).replaceAll(" ", "").toLowerCase()), state);
				
				maillog(date.format(now) + " - " + User_Managment.state.get(state.replaceAll(" ", "").toLowerCase()).get(i).trim() + " " + state + " "+ "Update Sent");
				User_Managment.totalMail = User_Managment.totalMail + 1;
			}
			eventlog("  -- " + date.format(now) + " All Mail Sent to " + state + " Subscribers");
				
		}
		else {
			eventlog(date.format(now) + " - PA DATA CHECK (No Update)");
			System.out.println("NO PA Update");
		}
		maillog("------------------------------------ Total Mail : " + User_Managment.totalMail + " ------------------------------------" );
	}
	
	
	public static void US () {
		String state = "United States";
		
		if (US__Web_Scrapper.USStatsCheck()) {
			eventlog(date.format(now) + " - US DATA CHECK (Update Needed)");
			
			//US__Web_Scrapper.USTotalcount();
			//US__Web_Scrapper.USNews();
			//US__Web_Scrapper.USStateCount();
			String HTMLcode = US_Html_Mail.USHTMLCreator();
			System.out.println("US Updated");
			
			for (int i = 0; i < User_Managment.state.get(state.replaceAll(" ", "").toLowerCase()).size(); i++) {
				//System.out.println(User_Managment.state.get(state).get(i).trim());
				//System.out.println(User_Managment.user.get(User_Managment.state.get(state).get(i).trim()));
				Mail_Sender.sendMail(HTMLcode, User_Managment.user.get(User_Managment.state.get(state.replaceAll(" ", "").toLowerCase()).get(i).trim().toLowerCase()), state);
				
				maillog(date.format(now) + " - " + User_Managment.state.get(state.replaceAll(" ", "").toLowerCase()).get(i).trim() + " " + state + " "+ "Update Sent");
				User_Managment.totalMail = User_Managment.totalMail + 1;
			}
			eventlog("  -- " + date.format(now) + " All Mail Sent to " + state + " Subscribers");
			maillog("------------------------------------ Total Mail : " + User_Managment.totalMail + " ------------------------------------" );
			
		}
		else {
			eventlog(date.format(now) + " - US DATA CHECK (No Update)");
			System.out.println("US  NO Update");
		}	
	}
	
	
	
	public static void LatestUpdate(String User, String state) {
		if (state.trim().toLowerCase() == "Pennsylvania".trim().toLowerCase()) {
			PA__Web_Scrapper.PACaseStats();
			PA__Web_Scrapper.PANews();
			String HTMLcode = PA_Html_Mail.PAHTMLCreator();
			
			Mail_Sender.sendMail(HTMLcode, User_Managment.user.get(User.trim().toLowerCase()), state);
			maillog(date.format(now) + " - " + User + " " + state + " "+ "Update Sent");
			User_Managment.totalMail = User_Managment.totalMail + 1;
			maillog("------------------------------------ Total Mail : " + User_Managment.totalMail + " ------------------------------------" );
		}
		else if (state.trim().toLowerCase() == "UnitedStates".trim().toLowerCase()) {
			String HTMLcode = US_Html_Mail.USHTMLCreator();
			
			Mail_Sender.sendMail(HTMLcode, User_Managment.user.get(User.trim().toLowerCase()), state);
			maillog(date.format(now) + " - " + User + " " + state + " "+ "Update Sent");
			User_Managment.totalMail = User_Managment.totalMail + 1;
			maillog("------------------------------------ Total Mail : " + User_Managment.totalMail + " ------------------------------------" );
		}
	}
	
	
	public static void CreateUser (String username, String Email, String state) {
		User_Managment.addUser(username, Email);
		User_Managment.userSub(username, state);
		
		Mail_Sender.sendMail(PA_Html_Mail.SubEmail(), Email , state);
		User_Managment.totalMail = User_Managment.totalMail + 1;
		
		maillog(date.format(now) + " " + username + " : " + Email + "subscribed");
		maillog(date.format(now) + " " + username + " : " + Email + "Thank You Mail Sent");
		
		maillog("------------------------------------- Total Mail : " + User_Managment.totalMail + " -------------------------------------" );
	}
	
	
	public static void mailTest (String html, String Email, String state ) {
		Mail_Sender.sendMail(html, Email, state);
	}
	
	//Functions to Log Data
	public static void eventlog (String data) {
		try {
			FileWriter writer = new FileWriter(eventLog,true);
			BufferedWriter bw = new BufferedWriter(writer);
		    PrintWriter out = new PrintWriter(bw);
		    
		    out.println(data);
		    out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void maillog (String data) {
		try {
			FileWriter writer = new FileWriter(mailLog,true);
			BufferedWriter bw = new BufferedWriter(writer);
		    PrintWriter out = new PrintWriter(bw);
		    
		    out.println(data);
		    out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
