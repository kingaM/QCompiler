package ast;

import visitor.Visitor;

public class PlusBinaryExpr extends BinaryExpr {

	public PlusBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public Object accept(Visitor v){
		return v.visit(this);
	}



}
