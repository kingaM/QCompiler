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
		return "Program [decllist=" + decllist.toString() + ", main=" + main.toString() + "]";
	}

	public ArrayList<Decl> getDecllist() {
		return decllist;
	}

	public MainDecl getMain() {
		return main;
	}	
}
