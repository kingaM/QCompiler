package ast;

import java.util.ArrayList;

import visitor.Visitor;

public class IfElseStmt extends Stmt{
	private Expr condition;
	private ArrayList<Stmt> ifBody;
	private ArrayList<Stmt> elseBody;
	
	public IfElseStmt(Expr condition, ArrayList<Stmt> ifBody, ArrayList<Stmt> elseBody){
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}

	public Expr getCondition() {
		return condition;
	}

	public void setCondition(Expr condition) {
		this.condition = condition;
	}

	public ArrayList<Stmt> getIfBody() {
		return ifBody;
	}

	public void setIfBody(ArrayList<Stmt> ifBody) {
		this.ifBody = ifBody;
	}

	public ArrayList<Stmt> getElseBody() {
		return elseBody;
	}

	public void setElseBody(ArrayList<Stmt> elseBody) {
		this.elseBody = elseBody;
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}


	@Override
	public String toString() {
		return "IfElseStmt [condition=" + condition + ", ifBody=" + ifBody
				+ ", elseBody=" + elseBody + "]";
	}		

}
