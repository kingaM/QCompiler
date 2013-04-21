package ast;

import visitor.Visitor;

public class NotEqCompBinaryExpr extends CompBinaryExpr {

	public NotEqCompBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public Object accept(Visitor v){
		return v.visit(this);
	}


}
