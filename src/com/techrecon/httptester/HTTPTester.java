/*
 * Tech Recon LLC
 * 1/14/2017-1/14/2017
 */

package com.techrecon.httptester;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;

public class HTTPTester {
	
	public static void main(String[] args) {
		
		//Name of character file tha contains list of URL's to be tested
		String fileName = "C:\\Users\\Christopher\\Desktop\\WS-Java\\HTTPTester\\src\\url-list";
		
		// This will reference one line at a time. Each line is an IP Address
		String line = null;
			
		FileOutputStream fileOutput = null;
		try {
			fileOutput = new FileOutputStream("C:\\Users\\Christopher\\Desktop\\WS-Java\\HTTPTester\\src\\com\\techrecon\\httptester\\html-response");
			PrintStream printOutput = new PrintStream(fileOutput);
        	System.setOut(printOutput);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            //Make "line" become each line read in the "url-list" file
            line = bufferedReader.readLine();//Reads only the first line of "ur-list"

            while(line != null) {
            	
                System.out.println(line);//Prints line from "url-list"
                ///////////
                URL tempURL = new URL("http://"+line);//Creates a URL object of line(IP Address) read from "url-list"
                URLConnection tempURLConnection = tempURL.openConnection();//Creates Connection from line read(IP Address)
                InputStreamReader  tempURLConnectionReader = new InputStreamReader(tempURLConnection.getInputStream()); //Reads Connection Output
                BufferedReader readConnection = new BufferedReader(tempURLConnectionReader);
                String connectionOutput;
                while((connectionOutput = readConnection.readLine()) != null) {
                	System.out.println(connectionOutput);//Print Connection Responses
                }
                readConnection.close();//Close Buffered Reader
                ///////////
                line = bufferedReader.readLine();//Reads next line in "url-list"
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
        	ex.printStackTrace();               
        }
        catch(IOException ex) {
        	System.out.println("Error");
        	System.out.println("HTTP Response Code: " + HttpURLConnection.HTTP_INTERNAL_ERROR);
            ex.printStackTrace();
        }
        
	}
}
