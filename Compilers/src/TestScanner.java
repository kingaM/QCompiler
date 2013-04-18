import java.io.*;

import ast.Program;

import java_cup.runtime.Symbol;

public class TestScanner {

	public static void main(String args[]) throws Exception {

		//System.setIn(new FileInputStream("testfile.txt"));
		System.setIn(new FileInputStream("test01"));
		Yylex myScanner = new Yylex(System.in);
		QCup myParser = new QCup(myScanner);

		Symbol result = myParser.parse();
		Program p = (Program) result.value;
		
		System.out.println("My result: " + p.toString());
	}
}