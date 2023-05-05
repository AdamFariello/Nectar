package databaseCode;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

//Note:
//Current working directory: ../Nectar/Server
//You get this from: System.getProperty("user.dir")

public class DBQuery extends DBInsert {
	//TODO: Add function to process scripts (for team members)
	@SuppressWarnings("static-access")
	public DBQuery(DBConnetion dbConnetion) {
		super.dbConnetion = dbConnetion;
	}
	
	public DBQuery(DBConnetion dbConnetion, String dataBaseFile) {
		super.dbConnetion = dbConnetion;
		activateScript();
	}
	
	public String setCurrentServer(String newdatabase) {
		return (dbConnetion.setCurrentServer(newdatabase));
	}
	
	
	
	//Script Methods
	public boolean initServer() {		
		//Testing System.getProperty("user.dir") in jshell
		//Will give user home directory since that's where java is setup
		//You can only test this in a test file in the project
		final String scriptName = "database.sql";
		final String workingDirectory = System.getProperty("user.dir");
		
		String scriptLocation;
		String operatingSystem = System.getProperty("os.name");
		
		//Script Location:
		//	Windows:
		//		C:\...\Nectar\server\database\database.sql
		//	Every other OS:
		//		/.../Nectar/server/database/database.sql
		if (operatingSystem.contains("Windows")) {	
			scriptLocation = System.getProperty("user.dir") + "\\" + scriptName;
			
			scriptLocation = String.format("source %s", sciptLocation);
		} else if (operatingSystem.contains("Mac")) {
			scriptLocation = String.format("source %s", sciptLocation);
		} else {
			scriptLocation = String.format("source %s", sciptLocation);
		}
		
		
		return activateScript(sciptLocation);
	}
	public boolean activateSqlScript(String scriptName) {
		
				
		return false;
	}
	public boolean activateSqlScript(String scriptName, String scriptDirectory) {
		
		
		return false;
	}
	private boolean executeScript(String command) {
		try {
			Process process = Runtime.getRuntime().exec(command);

			BufferedReader reader = new BufferedReader(
										new InputStreamReader(
											process.getInputStream()
									));
			
			String line;
			while ((line = reader.readLine()) != null) {System.out.println(line);}
			
			reader.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
