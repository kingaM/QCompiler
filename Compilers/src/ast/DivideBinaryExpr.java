package ast;

import visitor.Visitor;

public class DivideBinaryExpr extends BinaryExpr {

	public DivideBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public Object accept(Visitor v){
		return v.visit(this);
	}


}
