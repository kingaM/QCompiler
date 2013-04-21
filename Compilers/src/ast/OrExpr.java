package ast;

import visitor.Visitor;

public class OrExpr extends BinaryExpr{

	public OrExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public Object accept(Visitor v){
		return v.visit(this);
	}


}
