package edu.fullerton.csc323.lex;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Reader read;
		Lexer lex = new Lexer();
		String file = "";
		String input;
		Scanner sc = new Scanner(System.in);
		String commands[];
		
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		
		while (!file.equals("exit")) {
			System.out.println("To print tokens: [file name] tokens"
					+ "\nTo print grammar: [file name] grammar"
					+ "\n(file must be in /src/input/):");
			
			commands = sc.nextLine().split("\\s+");
			if (commands.length == 2) {
				read = new Reader(System.getProperty("user.dir")
						+ "/src/input/" + commands[0]);
			
				while ((input = read.nextLine()) != null)
					lex.tokenize(input.split("\\s+|^\\s+"));

				if (commands[1].equals("tokens"))
					lex.printTokens();
				else if (commands[1].equals("grammar"))
					lex.printGrammar();
				else
					System.out.println("ERROR: Print choice not recognized");
				lex.clear();
			}
			
			System.out.println("Press \"enter\" to read another file. "
					+ "Enter \"exit\" to quit.");
			file = sc.nextLine().toLowerCase();
		}
		
		sc.close();
		
	}

}
