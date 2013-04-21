package ast;

import visitor.Visitor;

public class EqCompBinaryExpr extends CompBinaryExpr {

	public EqCompBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public Object accept(Visitor v){
		return v.visit(this);
	}

	@Override
	public String toString() {
		
		return super.lhs + " == " + super.rhs + ";";
	}
}
