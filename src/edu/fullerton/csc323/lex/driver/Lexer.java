package edu.fullerton.csc323.lex.driver;

import java.util.LinkedList;
import java.util.List;

import edu.fullerton.csc323.lex.tokens.Token;

public class Lexer {
	private Token token;
	private List<String> tokens = new LinkedList<String>();
	private List<String> lexemes = new LinkedList<String>();
	private List<String> splitTokens = new LinkedList<String>();
	
	public Lexer () {
		token = new Token();
	}
	
	public void tokenize (String[] inputLine) {
		for (String s : inputLine) {
			tokens.add(tokenize(s));
			lexemes.add(s);
		}
		while (containsMultiToken())
			breakApartTokens();
	}
	
	private void tokenize (List<String> input, int index) {
		for (String s : input) {
			tokens.add(index, tokenize(s));
			lexemes.add(index, s);
		}
	}
	
	private String tokenize (String input) {
		if (token.isReserved(input))
			return "keyword";
		else if (token.isIdentifier(input))
			return "identifier";
		else if (token.isNumber(input))
			return "integer";
		else if (token.isOperator(input))
			return "operator";
		else if (token.isSeparator(input))
			return "separator";
		else if (token.containsOperator(input)
				|| token.containsSeparator(input))
			return "0";
		else
			return "unknown";
	}
	
	public String toString() {
		String temp = "Tokens \t\t|\tLexmes\n"
				+ "-------------------------------------\n";
		for (int i = 0; i < tokens.size(); i++)
			temp += tokens.get(i) + " \t|\t" + lexemes.get(i) + "\n";
		return temp;
	}
	
	private void breakApartTokens() {
		String temp;
		for (int i = 0; i < tokens.size(); i++) {
			if (tokens.get(i).equals("0")) {
				temp = lexemes.get(i);
				tokens.remove(i);
				lexemes.remove(i);
				splitTokens = token.breakApart(temp);
				tokenize(splitTokens, i);
			}
		}
	}
	
	private boolean containsMultiToken() {
		for (String s : tokens)
			if (s.equals("0"))
				return true;
		return false;
	}
	
	public void cleanEmptyLexemes() {
		for (int i = 0; i < lexemes.size(); i++) {
			if (lexemes.get(i).matches("\\s*")) {
				lexemes.remove(i);
				tokens.remove(i);
			}
		}
	}
}
