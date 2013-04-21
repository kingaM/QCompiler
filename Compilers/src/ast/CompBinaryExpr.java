package ast;

import visitor.Visitor;

public class CompBinaryExpr extends BinaryExpr {

	public CompBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public Object accept(Visitor v){
		return v.visit(this);
	}


}
