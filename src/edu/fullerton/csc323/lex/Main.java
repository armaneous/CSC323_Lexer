package edu.fullerton.csc323.lex;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Reader read;
		Lexer lex = new Lexer();
		String file = "";
		String input;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		
		while (!file.equals("exit")) {
			System.out.println("Enter file name to be read +"
					+ "\n(file must be in /src/input/):");
			
			file = sc.nextLine();
			read = new Reader(System.getProperty("user.dir")
					+ "/src/input/" + file);
			
			while ((input = read.nextLine()) != null)
				lex.tokenize(input.split("\\s+|^\\s+"));
			
			lex.print();
			lex.clear();
			
			System.out.println("Press \"enter\" to read another file. "
					+ "Enter \"exit\" to quit.");
			file = sc.nextLine().toLowerCase();
		}
		
		sc.close();
		
	}

}
