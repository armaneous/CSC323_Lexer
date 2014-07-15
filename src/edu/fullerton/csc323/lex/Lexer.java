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
	
}
