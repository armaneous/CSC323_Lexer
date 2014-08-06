package edu.fullerton.csc323.lex.grammar;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Rules {
	
	// Map for storing production rules
	private Map<String, String> rules = new HashMap<String, String>();
	private Map<String, String> productions = new HashMap<String, String>();

	private List<String> tokens = new LinkedList<String>();
	private List<String> lexemes = new LinkedList<String>();
	
	private Stack<String> st = new Stack<String>();
	
	public Rules(){
		initRules();
		initProductions();
	}
	
	public void setInput(List<String> t, List<String> l) {
		tokens = t;
		lexemes = l;
	}
	
	public void printRules() {
		convertToGrammar();
		lexemes.clear();
		for (int i = 0; i < st.size(); i++)
			lexemes.add(st.pop());
		
		for (String lex : lexemes)
			System.out.print(lex);
		
		System.out.println();
	}
	
	private void convertToGrammar() {
		String temp = "";
		for (int i = 0; i < tokens.size() && tokens.size() == lexemes.size(); i++) {
			if (tokens.get(i).equals("identifier"))
				lexemes.set(i, "<Identifier>");
			else if (tokens.get(i).equals("integer"))
				lexemes.set(i, "<Integer>");
		}
		for (int i = lexemes.size() - 1; i >= 0; i--) {
			if (productions.containsKey(lexemes.get(i)))
				st.push(productions.get(lexemes.get(i)) + " ");
			else {
				for (int j = i; j < lexemes.size(); j++) {
					temp += lexemes.get(j);
					if (productions.containsKey(temp))
						st.push(productions.get(temp));
					temp += " ";
				}
			}
		}
	}
	
	/**
	 * Initialize rules-to-productions map to list out all possible rules
	 */
	private void initRules() {
		rules.put("<Opt Function Definitions>",
				"<Function Definitions> | <Empty>");
		rules.put("<Function Definitions>",
				"<Function> | <Function> <Function Definition>");
		rules.put("<Function>",
				"function <Identifier> ( <Parameter> ) <Opt Declaration List> <Body>");
		rules.put("<Parameter>",
				"<Identifier> : <Qualifier>");
		rules.put("<Opt Declaration List>",
				"<Declaration List> | <Empty>");
		rules.put("<Declaration List>",
				"<Declaration> | <Declaration> ; <Declaration List>");
		rules.put("<Declaration>",
				"<Qualifier> <IDs>");
		rules.put("<Qualifier>",
				"int | boolean");
		rules.put("<IDs>",
				"<Identifier> | <Identifier> , <IDs>");
		rules.put("<Body>",
				"{ <Statement List> }");
		rules.put("<Statement List>",
				"<Statement> | <Statement> <Statement List>");
		rules.put("<Statement>",
				"<Compound> | <Assign> | <If> | <Return> | <Write> | <Read> | <While>");
		rules.put("<Compund>",
				"{ <Statement List> }");
		rules.put("<Assign>",
				"<Identifier> := <Expression> ;");
		rules.put("<If>",
				"if ( <Condition> ) <Statement> <If Prime>");
		rules.put("<If Prime>",
				"fi | else <Statement> fi");
		rules.put("<Return>",
				"return <Expression> ;");
		rules.put("<Write>",
				"write ( <Expression> ) ;");
		rules.put("<Read>",
				"read ( <IDs> ) ;");
		rules.put("<While>",
				"while ( <Condition> ) <Statement>");
		rules.put("<Condition>",
				"<Expression> <Relop> <Expression>");
		rules.put("<Relop>",
				"== | != | > | <");
		rules.put("<Expression>",
				"<Term> <Expression Prime>");
		rules.put("<Expression Prime>",
				"+ <Term> <Expression Prime> | - <Term> <Expression Prime> | <Empty>");
		rules.put("<Term>",
				"<Factor> <Term Prime>");
		rules.put("<Term Prime>",
				"* <Factor> <Term Prime> | / <Factor> <Term Prime> | <Empty>");
		rules.put("<Factor>",
				"- <Primary> | <Primary>");
		rules.put("<Primary>",
				"<Identifier> | <Integer> | <Identifier> ( <Empty> ) | ( <Expression> ) | true | false");
		rules.put("<Empty>",
				"E");
	}
	
	/**
	 * Initialize productions-to-rule mapping (inverse mapping of rules)
	 */
	private void initProductions() {
		for (String s : rules.keySet()) {
			if (rules.get(s).contains("|"))
				for (String t : rules.get(s).split(" \\| "))
					productions.put(t, s);
			else
				productions.put(rules.get(s), s);
		}
	}
}
