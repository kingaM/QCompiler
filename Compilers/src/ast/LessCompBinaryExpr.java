package ast;

import visitor.Visitor;

public class LessCompBinaryExpr extends CompBinaryExpr {

	public LessCompBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}



}
