package edu.fullerton.csc323.lex;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
	
	// To store array of items per line
	private String[] items = new String[2];

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
	
	// Separators
	private char[] separators = {';', '(', ')', '{', '}', '5', '6', '7', '8', '9'};
	
	/**
	 * Run analysis on input array to tokenize items properly
	 * @param input	:	String[] to be tokenized
	 */
	public void tokenize(String[] input){
		for (String item : input){
			tokens.add(analyze(item));
			lexemes.add(item);
		}
	}
	
	/**
	 * Private helper method to use a String and return an appropriate
	 * token as a String value.
	 * @param s	:	String to be tokenized
	 * @return	:	String value of token from input String
	 */
	private String analyze(String s) {
		if (isReserved(s))
			return "keyword";
		else if (isIdentifier(s))
			return "identifier";
		else if (isInteger(s))
			return "integer";
		else if (isOp(s))
			return "operator";
		else if (s.length() == 1 && isSeparator(s.charAt(0)))
			return "separator";
		else if (startsWithSeparator(s)) {
			items[0] = s.substring(0, 1);
			items[1] = s.substring(1);
			tokenize(items);
			return "0";
		}
		else if (endsWithSeparator(s)) {
			items[0] = s.substring(0, s.length()-1);
			items[1] = s.substring(s.length()-1);
			tokenize(items);
			return "0";
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
	 * Prints the tokens and lexemes Lists
	 */
	public void print(){
		cleanTokensLexemes();
		
		if(tokens.size() != lexemes.size()){
			System.out.println("ERROR: Tokens and Lexemes have "
					+ "different sizes");
			return;
		}
		
		System.out.println("Tokens \t\tLexemes");
		for (int i = 0; i < tokens.size(); i++)
			System.out.println(tokens.get(i)
					+ " \t\t" + lexemes.get(i));
	}
	
	/**
	 * Private helper method for removing empty tokens that were
	 * added when compound tokens were analyzed.
	 */
	private void cleanTokensLexemes(){
		while (tokens.contains("0")) {
			lexemes.remove(tokens.indexOf("0"));
			tokens.remove(tokens.indexOf("0"));
		}
	}
	
	/**
	 * Determine if String is an identifier. Identifier must start
	 * with a-z or A-Z and can contain _ and 0-9.
	 * @param s	:	String to be checked
	 * @return	:	true if String is identifier, false otherwise
	 */
	private boolean isIdentifier(String s){
		boolean startsWithLetter = isLetter(s.charAt(0));
		boolean correctSyntax = s.matches("[a-z]+[A-Z]*.*_*[0-9].*");
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
	
	/**
	 * Private helper method to determine if char is separator or not.
	 * @param c	:	char to be checked
	 * @return	:	true if char is separator, false otherwise
	 */
	private boolean isSeparator(char c){
		for(char h : separators)
			if(c == h)
				return true;
		return false;
	}
	
	/**
	 * Check to see if String is concatenated with a separator and
	 * the separator is at the beginning of the String.
	 * @param s	:	String to be checked
	 * @return	:	true if String starts with a separator
	 */
	private boolean startsWithSeparator(String s){
		return isSeparator(s.charAt(0));
	}
	
	/**
	 * Check to see if String is concatenated with a separator and
	 * the separator is at the end of the String.
	 * @param s	:	String to be checked
	 * @return	:	true if String ends with a separator
	 */
	private boolean endsWithSeparator(String s){
		return isSeparator(s.charAt(s.length()-1));
	}
}
