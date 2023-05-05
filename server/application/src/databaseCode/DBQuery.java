package databaseCode;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
		initServer();
	}
	
	public String setCurrentServer(String newdatabase) {
		return (dbConnetion.setCurrentServer(newdatabase));
	}
	
	
	
	//Script Methods
	public boolean initServer() { return initServer("database.sql");}
	public boolean initServer(String scriptName) {
		//Working Directory Location using System.getProperty("user.dir"):
		//	Windows: "C:\...\Nectar\server"
		//	Every other OS: "/.../Nectar/server"
		String workingDir = System.getProperty("user.dir");
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {	
			return executeScript(String.format("%s\\database\\%s", workingDir, scriptName));			
		} else {
			String command = String.format("%s/database/%s", workingDir, scriptName);
			System.out.println(command);
			return executeScript(command);
		}	
	}
	public boolean initServer(String dirOfScript, String scriptName) {
		//Just going to assume the slash at the end is given
		return executeScript(String.format("%s%s", dirOfScript, scriptName));
	}
	
	private boolean executeScript(String filePath) {
		try {
			/*
            ProcessBuilder pb = new ProcessBuilder(
            	"sh", "-c", "mysql", 
            	"-u", "admin", 
            	"-p", "password",
            	"<", filePath
            );
            // Exception thrown here because folder
            // structure of Windows and Linux are different.
            pb.directory(
                new File(System.getProperty("user.home")));
            // It will throw and exception
            Process process = pb.start();
            */
            
			String user 	= "admin";
			String password = "password";
			String format  = "/bin/sh -c mysql -u %s -p%s < \"%s\"";
			String command = String.format(format, user, password, filePath);
			System.out.println(command);
			Process p = Runtime.getRuntime().exec(command);
			p.waitFor();
			p.destroy();
			return true;
			
			/*
			PreparedStatement ps = dbConnetion.getConnection()
											  .prepareStatement(command);	
			if (ps.executeUpdate() > 0) {
				System.out.println("Success");
				return true;
			}
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
