package ast;

import java.util.ArrayList;

public class MainDecl {
	
	private ArrayList<Stmt> stmts = new ArrayList<Stmt>();

	@Override
	public String toString() {
		return "MainDecl [stmts=" + stmts.toString() + "]";
	}

	public ArrayList<Stmt> getStmts() {
		return stmts;
	}

	
}
