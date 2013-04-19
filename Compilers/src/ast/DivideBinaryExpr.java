package ast;

import visitor.Visitor;

public class DivideBinaryExpr extends BinaryExpr {

	public DivideBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}


}
