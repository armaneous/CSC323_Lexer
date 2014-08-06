package edu.fullerton.csc323.lex;

import java.util.Scanner;

import edu.fullerton.csc323.lex.driver.Lexer;
import edu.fullerton.csc323.lex.io.Reader;

public class Main {

	public static void main(String[] args) {
		Reader read;
		String file = "";
		String input;
		Scanner sc = new Scanner(System.in);
		
		print("Working Directory = " + System.getProperty("user.dir"));
		
		while (!file.equals("exit")) {
			Lexer lex = new Lexer();
			
			print("To print tokens, enter file name"
					+ "\n(file must be in /src/input/):");
			
			file = System.getProperty("user.dir")
					+ "/src/input/" + sc.nextLine();
			
			read = new Reader(file);
			
			while ((input = read.nextLine()) != null)
				lex.tokenize(input.split("\\s+|^\\s+"));
			
			lex.cleanEmptyLexemes();
			
			System.out.println(lex);
			
			print("Press \"enter\" to read another file. "
					+ "Enter \"exit\" to quit.");
			file = sc.nextLine().toLowerCase();
		}
		
		sc.close();
		
	}
	
	private static void print(String s) {
		System.out.println(s);
	}

}
