package ast;

import visitor.Visitor;

public class GreaterCompBinaryExpr extends CompBinaryExpr {

	public GreaterCompBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}


}
