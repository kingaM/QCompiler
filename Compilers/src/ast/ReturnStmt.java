package ast;

import visitor.Visitor;

public class ReturnStmt extends Stmt{
	private Expr returnExpr;

	public ReturnStmt(Expr returnExpr) {
		super();
		this.returnExpr = returnExpr;
	}

	public Expr getReturnExpr() {
		return returnExpr;
	}

	public void setReturnExpr(Expr returnExpr) {
		this.returnExpr = returnExpr;
	}
	
	public Object accept(Visitor v){
		return v.visit(this);
	}

	@Override
	public String toString() {
		return "return " + returnExpr + ";";
	}
}
