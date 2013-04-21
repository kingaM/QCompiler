package ast;

import visitor.Visitor;

public class GreaterCompBinaryExpr extends CompBinaryExpr {

	public GreaterCompBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public Object accept(Visitor v){
		return v.visit(this);
	}


}
