package Application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class User_Managment {
	
	static HashMap<String, String> user = new HashMap<String, String>(); // Username - Email Address
	
	static HashMap<String, ArrayList<String>> state = new HashMap<String, ArrayList<String>>(); // States - Users Subscribed to that states
	
	static int totalMail = 0;
	
	public static void dataImport (){
		importUserFile();
		importStateFile();
		//importTotalMail();
	}
	
	public static void dataUpdate () {
		updateUserFile();
		updateStateFile();
		//updateTotalMail();
	}
	
	
	/**
	 * 
	 * @param username
	 * @param subState
	 */
	public static void userSub(String username, String subState) {
		state.get(subState.replaceAll(" ", "").toLowerCase()).add(username.replaceAll(" ", "").toLowerCase());
	}
	
	
	/**
	 * This will add the name of the state to the system.
	 * @param stateName
	 */
	public static void addState(String stateName) {
		state.put(stateName.replaceAll(" ", "").toLowerCase(), new ArrayList<String>());
		
	}
	
	/**
	 * This will add user to the system.
	 * @param username
	 * @param Email
	 */
	public static void addUser(String username, String Email) {
		user.put(username.replaceAll(" ", "").toLowerCase(), Email.replaceAll(" ", "").toLowerCase());
	}
	
	/**
	public static void updateTotalMail() {
		try {
			File Mails = new File("TotalMail.txt");
			FileWriter Writer = new FileWriter(Mails);
			Writer.write(Integer.toString(totalMail + 1));
			Writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void importTotalMail() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("TotalMail.txt"));
			if (reader.readLine() == "" | reader.readLine() == "0" | reader.readLine() == null) {
				System.out.println(reader.readLine());
			}
			else {
				totalMail = Integer.parseInt(reader.readLine());
				System.out.println(Integer.parseInt(reader.readLine()));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}**/
	
	
	/**
	 * This will update the state file so that we can import the data when we run the program
	 */
	public static void updateStateFile() {
		try {
			File States = new File("States.txt");
			FileWriter Writer = new FileWriter(States);
			for(Entry<String, ArrayList<String>> entry : state.entrySet()){
                Writer.write( entry.getKey().replaceAll(" ", "").toLowerCase() + ":" + entry.getValue());
                Writer.write("\n");
            }
			Writer.close(); 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void importStateFile() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("States.txt"));
			String lineContent = null;
			while ((lineContent = reader.readLine()) != null) {
				ArrayList<String> stateArray = new ArrayList<String>(Arrays.asList(lineContent.split(":")));	
				ArrayList<String> subArray = new ArrayList<String>(Arrays.asList(stateArray.get(1).substring(1, (stateArray.get(1).length()) - 1).replaceAll("\\s+", "").toLowerCase().split(",")));
				state.put(stateArray.get(0).replaceAll(" ", "").toLowerCase(), subArray);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * This will update the Users file so that we can import the date when we run the program
	 */
	public static void updateUserFile() {
		try {
			File Users = new File("Users.txt");
			FileWriter Writer = new FileWriter(Users);
			for(Entry<String, String> entry : user.entrySet()){
                Writer.write( entry.getKey().replaceAll(" ", "").toLowerCase() + ":" + entry.getValue().replaceAll(" ", "").toLowerCase() );
                Writer.write("\n");
            }
			Writer.close(); 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void importUserFile() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("Users.txt"));
			String lineContent = null;
			while ((lineContent = reader.readLine()) != null) {
				ArrayList<String> userArray = new ArrayList<String>(Arrays.asList(lineContent.split(":")));		
				user.put(userArray.get(0).replaceAll(" ", "").toLowerCase(), userArray.get(1).replaceAll(" ", "").toLowerCase());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}	
