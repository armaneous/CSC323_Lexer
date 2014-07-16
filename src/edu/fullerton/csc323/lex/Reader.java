package edu.fullerton.csc323.lex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class Reader {
	private BufferedReader br;
	private String line;

	public Reader(String file){
		URL src = getClass().getResource(file);
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File does not exist. ");
		}
	}
	
	public String nextLine() {
		try {
			if((line = br.readLine()) != null)
				return line;
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getStackTrace());
		}
		return null;
	}
	
	public void closeReader(){
		try {
			br.close();
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getStackTrace());
		}
	}
}