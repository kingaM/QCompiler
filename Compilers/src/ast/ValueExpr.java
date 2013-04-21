package ast;

import visitor.Visitor;

public class ValueExpr extends Expr {

	public ValueExpr() {
		// TODO Auto-generated constructor stub
	}

	public Object accept(Visitor v){
		return v.visit(this);
	}
}
