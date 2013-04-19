package ast;

import visitor.Visitor;

public class GreaterOrEqExpr extends BinaryExpr{

	public GreaterOrEqExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}


}
