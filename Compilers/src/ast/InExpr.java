package ast;

import visitor.Visitor;

public class InExpr extends BinaryExpr{

	public InExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}


}
