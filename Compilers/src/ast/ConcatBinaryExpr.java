package ast;

import visitor.Visitor;

public class ConcatBinaryExpr extends BinaryExpr {

	public ConcatBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public Object accept(Visitor v){
		return v.visit(this);
	}


}