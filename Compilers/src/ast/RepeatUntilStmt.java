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

	public void setCondition(Expr condition) {
		this.condition = condition;
	}

	public ArrayList<Stmt> getBody() {
		return body;
	}

	public void setBody(ArrayList<Stmt> body) {
		this.body = body;
	}

	public Object accept(Visitor v){
		return v.visit(this);
	}


	@Override
	public String toString() {
		return "RepeatUntilStmt [condition=" + condition + ", body=" + body
				+ "]";
	}
}
