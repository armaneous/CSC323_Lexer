package edu.fullerton.csc323.lex;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
	
	private String line;
	
	// To store array of items per line
	private String[] items;

	// Store tokens and lexemes to be printed
	private List<String> tokens = new ArrayList<String>();
	private List<String> lexemes = new ArrayList<String>();
	
	// Reserved keywords
	private String[] reserved = {"boolean", "else", "false", "fi", "function",
			"if", "int", "read", "return", "true", "while", "write"};
	
	// Letters
	private char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
			'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

	// Numbers
	private char[] nums = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	// Operators
	private String[] ops = {":=", "+", "-", "*", "/", "<", ">", "==", "!="};
	
	/**
	 * Run analysis on input array to tokenize items properly
	 * @param input	:	String[] to be tokenized
	 */
	public void analyzeLex(String[] input){
		for (String item : input){
			tokens.add(tokenize(item));
			lexemes.add(item);
		}
	}
	
	/**
	 * Private helper method to use a String and return an appropriate
	 * token as a String value.
	 * @param s	:	String to be tokenized
	 * @return	:	String value of token from input String
	 */
	private String tokenize(String s) {
		if (isReserved(s)) {
			return "keyword";
		}
		else if (isIdentifier(s)) {
			return "identifier";
		}
		else if (isInteger(s)) {
			return "integer";
		}
		else if (isOp(s)) {
			return "operator";
		}
		return "unknown";
	}
	
	/**
	 * Check String to see if it is a reserved keyword or not.
	 * @param s	:	String to be checked against keyword array.
	 * @return	:	true if String is a keyword, false otherwise
	 */
	private boolean isReserved(String s){
		for(String word : reserved)
			if(s.equals(word)) return true;
		return false;
	}
	
	/**
	 * Print a List of Strings to console.
	 * @param list	:	List to be printed
	 */
	private void print(List<String> list){
		for(String s : list)
			System.out.print(s + " ");
		System.out.println();
	}
	
	/**
	 * Determine if String is an identifier. Identifier must start
	 * with a-z or A-Z and can contain _ and 0-9.
	 * @param s	:	String to be checked
	 * @return	:	true if String is identifier, false otherwise
	 */
	private boolean isIdentifier(String s){
		boolean startsWithLetter = isLetter(s.charAt(0));
		boolean correctSyntax = s.matches("[a-Z].*_*[0-9]*|[a-Z].*[0-9]*_*");
		return startsWithLetter && correctSyntax;
	}
	
	/**
	 * Private helper method to determine if char is letter or not.
	 * @param c	:	char to be checked
	 * @return	:	true if char is letter, false otherwise
	 */
	private boolean isLetter(char c){
		for(char h : chars)
			if(c == h)
				return true;
		return false;
	}
	
	/**
	 * Determine if String is an integer. Integer must start with 0-9.
	 * @param s	:	String to be checked
	 * @return	:	true if String is identifier, false otherwise
	 */
	private boolean isInteger(String s){
		boolean startsWithNumber = isNum(s.charAt(0));
		boolean correctSyntax = s.matches("[0-9].*");
		return startsWithNumber && correctSyntax;
	}
	
	/**
	 * Private helper method to determine if char is number or not.
	 * @param c	:	char to be checked
	 * @return	:	true if char is number, false otherwise
	 */
	private boolean isNum(char c){
		for(char h : nums)
			if(c == h)
				return true;
		return false;
	}
	
	/**
	 * Private helper method to determine if String is an operator or not.
	 * @param s	:	String to be checked
	 * @return	:	true if String is operator, false otherwise
	 */
	private boolean isOp(String s){
		for(String o : ops)
			if(s.equals(o))
				return true;
		return false;
	}
}
