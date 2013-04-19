package ast;

import visitor.Visitor;

public class PlusBinaryExpr extends BinaryExpr {

	public PlusBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}



}
