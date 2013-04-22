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
		String ifs = "";
		for(int i = 0; i < body.size(); i++){
			ifs = ifs + body.get(i).toString();
		}
		return "repeat {\n" + ifs + "\n} until (\n"  + condition + "\n)\n";

	}
}
