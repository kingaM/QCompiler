package ast;

import visitor.Visitor;

public class CompBinaryExpr extends BinaryExpr {

	public CompBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}


}
