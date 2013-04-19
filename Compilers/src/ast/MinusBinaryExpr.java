package ast;

import visitor.Visitor;

public class MinusBinaryExpr extends BinaryExpr {

	public MinusBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}


}
