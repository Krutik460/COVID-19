package Application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class totalMailCounter {
	
	private static int totalMail = 0;
	private static int totalDays = 0;
	
	public static void main(String[] args ) {
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader("mailLog.txt"));
			String lineContent = null;
			
			String day = "";
			
			while ((lineContent = reader.readLine()) != null) {
				
				 if (lineContent.contains("--")){
					 
					 totalMail = totalMail + Integer.parseInt(lineContent.replaceAll("\\D+", ""));
				 }
				 else {
					 if (!lineContent.substring(0, 10).equals(day)) {
						totalDays += 1;
						day = lineContent.substring(0, 10);
					 }
				 }
			}
			
			System.out.println("TOTAL MAIL : " + totalMail + " Sent");
			System.out.println("In " + totalDays + " Days");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
