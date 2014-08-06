package edu.fullerton.csc323.lex.tokens;

import java.util.LinkedList;
import java.util.List;

public class Token {
	private String[] reserved = {
			"boolean",
			"else",
			"false",
			"fi",
			"function",
			"if",
			"int",
			"read",
			"return",
			"true",
			"while",
			"write"	
	};
	private String[] separators = {
			"{",
			"(",
			":",
			",",
			")",
			"}",
			";",
			"$$"
	};
	private String[] operators = {
			":=",
			"+",
			"-",
			"*",
			"/",
			"<",
			">",
			"==",
			"!="
	};
	
	private List<String> splitTokens = new LinkedList<String>();
	
	public boolean isReserved (String input) {
		return isInsideSet(input, reserved);
	}
	
	public boolean isSeparator (String input) {
		return isInsideSet(input, separators);
	}
	
	public boolean isOperator (String input) {
		return isInsideSet(input, operators);
	}
	
	public boolean isIdentifier (String input) {
		return input.matches("[a-z]+([a-z]|[A-Z]|[0-9]|_)*");
	}
	
	public boolean isNumber (String input) {
		return input.matches("[0-9]+");
	}
	
	public boolean containsOperator (String input) {
		return containsFromSet(input, operators);
	}
	
	public boolean containsSeparator (String input) {
		return containsFromSet(input, separators);
	}
	
	private boolean containsFromSet (String input, String[] set) {
		for (String s : set)
			if (input.contains(s))
				return true;
		return false;
	}
	
	private boolean isInsideSet(String input, String[] set) {
		for (String s : set)
			if (input.equals(s))
				return true;
		return false;
	}
	
	public List<String> breakApart(String multiToken) {
		splitTokens.clear();
		if (containsOperator(multiToken)) {
			breakApart(multiToken, operators);
		}
		else if (containsSeparator(multiToken)) {
			breakApart(multiToken, separators);
		}
		return splitTokens;
	}
	
	private void breakApart(String multiToken, String[] set) {
		String[] split;
		for (String s : set) {
			if (multiToken.startsWith(s)) {
				splitTokens.add(multiToken.substring(s.length()));
				splitTokens.add(multiToken.substring(0, s.length()));
				break;
			}
			else if (multiToken.endsWith(s)) {
				splitTokens.add(multiToken.substring(multiToken.length() - s.length()));
				splitTokens.add(multiToken.substring(0, multiToken.length() - s.length()));
				break;
			}
			else if (multiToken.contains(s)) {
				split = multiToken.split("\\" + s);
				splitTokens.add(split[1]);
				splitTokens.add(s);
				splitTokens.add(split[0]);
				break;
			}
		}
	}
}
