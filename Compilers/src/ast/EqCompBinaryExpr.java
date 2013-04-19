package ast;

import visitor.Visitor;

public class EqCompBinaryExpr extends CompBinaryExpr {

	public EqCompBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}


}
