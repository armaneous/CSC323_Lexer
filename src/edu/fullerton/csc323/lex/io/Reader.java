package edu.fullerton.csc323.lex.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	private BufferedReader br;
	private String line;

	public Reader(String file){
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