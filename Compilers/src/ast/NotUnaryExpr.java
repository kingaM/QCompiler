package ast;

import visitor.Visitor;

public class NotUnaryExpr extends Expr{
	private Expr expr;

	public NotUnaryExpr(Expr expr) {
		super();
		this.expr = expr;
	}

	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
	}
	
	public Object accept(Visitor v){
		return v.visit(this);
	}


}
