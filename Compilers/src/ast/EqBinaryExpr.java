package ast;

import visitor.Visitor;

public class EqBinaryExpr extends BinaryExpr {

	public EqBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}


}
