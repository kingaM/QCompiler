package ast;

import visitor.Visitor;

public class LessEqCompBinaryExpr extends CompBinaryExpr {

	public LessEqCompBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}


}
