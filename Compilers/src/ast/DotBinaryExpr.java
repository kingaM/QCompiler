package ast;

import visitor.Visitor;

public class DotBinaryExpr extends BinaryExpr {

	public DotBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public Object accept(Visitor v){
		return v.visit(this);
	}


}
