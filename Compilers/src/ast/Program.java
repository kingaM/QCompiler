package ast;

import java.util.ArrayList;

import visitor.Visitor;

public class Program extends Node {
	
	private ArrayList<Decl> decllist;
	private MainDecl main;
	
	public Program(ArrayList<Decl> l, MainDecl m) {
		decllist = l;
		main = m;
	}
	
    public Object accept(Visitor v){
		return v.visit(this);
	}
	
	@Override
	public String toString() {
		String toprint = "\nProgram: \n\n";
		for(Decl d : decllist){
			toprint = toprint + d;
		}
		toprint = toprint + "\n{\n" + main.toString() + "\n}\n";
		return toprint;
		//return "Program: \n\n" + decllist.toString() + "\n{\n" + main.toString() + "\n}\n";
		//return "Program [decllist=" + decllist.toString() + ", main=" + main.toString() + "]";
	}

	public ArrayList<Decl> getDecllist() {
		return decllist;
	}

	public MainDecl getMain() {
		return main;
	}	
}
