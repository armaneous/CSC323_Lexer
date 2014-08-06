package edu.fullerton.csc323.lex.tokens;

public enum Tokens {
	keyword("keyword"),
	operator("operator"),
	identifier("identifier"),
	integer("integer"),
	separator("separator"),
	unknown("unknown"),
	multi("multi");
	
	String name;
	
	Tokens(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
