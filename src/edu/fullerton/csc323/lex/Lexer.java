package edu.fullerton.csc323.lex;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
	
	// To store array of items per line
	private String[] items = new String[2];
	
	// Store temporary String
	private String temp;

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
	private String[] separators = {";", "(", ")", "{", "}", ",", ":", "$$"};
	
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
		// Check if current String is a reserved keyword
		if (isReserved(s))
			return "keyword";
		// Check if current String is an identifier
		else if (isIdentifier(s))
			return "identifier";
		// Check if current String is an integer
		else if (isInteger(s))
			return "integer";
		// Check if current String is an operator
		else if (isOp(s))
			return "operator";
		// Check if current String is a separator
		else if (isSeparator(s))
			return "separator";
		// Check if current String contains an operator
		// (This assumes length > 1)
		else if (containsOperator(s)) {
			splitFromTokens(s, ops);
			return "0";
		}
		// Check if current String contains a separator
		// (This assumes length > 1)
		else if (containsSeparator(s)) {
			splitFromTokens(s, separators);
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
		
		// Double-check if tokens and lexemes are same size
		// to avoid any NullPointer exceptions
		if(tokens.size() != lexemes.size()){
			System.out.println("ERROR: Tokens and Lexemes have "
					+ "different sizes");
			return;
		}
		
		// Begin printing tokens and lexemes
		System.out.println("Tokens \t\t|\tLexemes");
		System.out.println("------------------------------------");
		for (int i = 0; i < tokens.size(); i++)
			System.out.println(tokens.get(i)
					+ " \t|\t" + lexemes.get(i));
		System.out.println();
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
	 * Clears tokens and lexemes for reuse when running Lexer
	 * again in the same program process.
	 */
	public void clear(){
		tokens.clear();
		lexemes.clear();
	}
	
	/**
	 * Determine if String is an identifier. Identifier must start
	 * with a-z or A-Z and can contain _ and 0-9.
	 * @param s	:	String to be checked
	 * @return	:	true if String is identifier, false otherwise
	 */
	private boolean isIdentifier(String s){
		if (s.length() >= 1)
			return isLetter(s.charAt(0)) && s.matches("[a-z]+[A-Z]*_*[0-9]*");
		return false;
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
		if (s.length() >= 1)
			return isNum(s.charAt(0)) && s.matches("[0-9]+");
		return false;
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
	private boolean isSeparator(String s){
		for(String p : separators)
			if(s.equals(p))
				return true;
		return false;
	}
	
	/**
	 * Scan whole String for separators concatenated with other
	 * tokens both before and after separator.
	 * @param s	:	String to be checked
	 * @return	:	true if String contains a separator
	 */
	private boolean containsSeparator(String s){
		for (String p : separators)
			if (s.contains(p))
				return true;
		return false;
	}
	
	/**
	 * Deal with splitting String from symbols for tokenizing. Symbols
	 * can be operators or separators.
	 * @param s			:	String to be split	
	 * @param symbols	:	String[] to be check against for splitting
	 */
	private void splitFromTokens(String s, String[] symbols){
		int index;
		// For every symbol
		for (String p : symbols){
			index = s.indexOf(p);
			// If the symbol is found in the String
			if (index != -1){
				items[0] = (index == 0)
						? s.substring(0, index+1) : s.substring(0, index);
				items[1] = (index == 0)
						? s.substring(index+1) : s.substring(index);
				System.out.println("Split: " + items[0]+ " " + items[1] + " Temp:" + s);
				System.out.println("Length: " + s.length() + " Index:" + index);
				tokenize(items);
			}
			else {
				// TODO Repeating separators/operators in single String
			}
		}
	}
	
	/**
	 * Scan whole String for operators concatenated with other
	 * tokens both before and after operator.
	 * @param s	:	String to be checked
	 * @return	:	true if String contains a operator
	 */
	private boolean containsOperator(String s){
		for (String p : ops)
			if (s.contains(p))
				return true;
		return false;
	}
}
