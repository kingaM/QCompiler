package ast;

import visitor.Visitor;

public class InExpr extends BinaryExpr{

	public InExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public Object accept(Visitor v){
		return v.visit(this);
	}


}
