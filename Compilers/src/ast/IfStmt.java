package ast;

import java.util.ArrayList;

public class IfStmt extends Stmt{
	private Expr condition;
	private ArrayList<Stmt> body;
	
	public IfStmt(Expr condition, ArrayList<Stmt> body){
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

	@Override
	public String toString() {
		return "IfStmt [condition=" + condition + ", body=" + body + "]";
	}
	
}
