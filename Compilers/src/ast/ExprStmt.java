package ast;

import visitor.Visitor;

public class ExprStmt extends Stmt {
	
	private Expr e;

	public ExprStmt(Expr e) {
		this.e = e;
	}
	
	public Expr getE() {
		return e;
	}
	
	public Object accept(Visitor v){
		return v.visit(this);
	}


	@Override
	public String toString() {
		return "ExprStmt [" + (e != null ? "e=" + e : "") + "]";
	}

}
