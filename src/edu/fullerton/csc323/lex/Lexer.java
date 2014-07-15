package edu.fullerton.csc323.lex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Lexer {
	
	private String line;
	
	// To store array of items per line
	private String[] items;
	
	// Store tokens to be printed
	private List<String> tokens = new LinkedList<String>();
	
	// Reserved keywords
	private String[] reserved = {"bool", "break", "class", "double", "else", "extends", "for", "if",
			"implements", "int", "interface", "newarray", "println", "readln", "return", "string",
			"void", "while"};
	
	// Letters
	private char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
			'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
			'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z'};
	
	/**
	 * Print list to console
	 * @param list	:	List to be printed
	 */
	private void print(List<String> list){
		for(String s : list)
			System.out.print(s + " ");
		System.out.println();
	}
	
	/**
	 * Determine if char is letter or not
	 * @param c	:	char to be checked
	 * @return	:	true if char is letter, false otherwise
	 */
	private boolean isLetter(char c){
		for(char h : chars)
			if(c == h)
				return true;
		return false;
	}
}
