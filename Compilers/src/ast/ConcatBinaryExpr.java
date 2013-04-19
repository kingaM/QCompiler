package ast;

import visitor.Visitor;

public class ConcatBinaryExpr extends BinaryExpr {

	public ConcatBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}


}
