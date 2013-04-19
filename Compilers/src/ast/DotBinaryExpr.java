package ast;

import visitor.Visitor;

public class DotBinaryExpr extends BinaryExpr {

	public DotBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}


}
