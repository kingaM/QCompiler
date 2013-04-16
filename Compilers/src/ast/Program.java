package ast;

import java.util.ArrayList;

public class Program {
	
	private ArrayList<Decl> decllist;
	private MainDecl main;
	
	public Program(ArrayList<Decl> l, MainDecl m) {
		decllist = l;
		main = m;
	}

	@Override
	public String toString() {
		return "Program [decllist=" + decllist.toString() + ", main=" + main.toString() + "]";
	}
	
	
	
}
