package ast;

import visitor.Visitor;

public class NotEqCompBinaryExpr extends CompBinaryExpr {

	public NotEqCompBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}


}
