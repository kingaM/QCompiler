package ast;

import visitor.Visitor;

public class ValueExpr extends Expr {

	public ValueExpr() {
		// TODO Auto-generated constructor stub
	}

	public void accept(Visitor v){
		v.visit(this);
	}
}
