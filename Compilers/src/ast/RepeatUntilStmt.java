package ast;

import java.util.ArrayList;

import visitor.Visitor;

public class RepeatUntilStmt extends Stmt{
	private Expr condition;
	private ArrayList<Stmt> body;
	
	public  RepeatUntilStmt(Expr condition, ArrayList<Stmt> body) {
		this.condition = condition;
		this.body = body;
	}

	public Expr getCondition() {
		return condition;
	}

	public ArrayList<Stmt> getBody() {
		return body;
	}
	
	public Object accept(Visitor v){
		return v.visit(this);
	}


	@Override
	public String toString() {
		return "repeat {\n" + body + "\n} until (\n"  + condition + "\n)\n";

	}
}
