package ast;

import java.util.ArrayList;

import visitor.Visitor;

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
	
	public Object accept(Visitor v){
		return v.visit(this);
	}


	@Override
	public String toString() {
		String ifs = "";
		for(int i = 0; i < body.size(); i++){
			ifs = ifs + body.get(i).toString();
		}
		return "if(" + condition + ")" + "{\n" + ifs + "\n}";
	}
	
}
