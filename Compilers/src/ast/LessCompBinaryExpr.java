package ast;

import visitor.Visitor;

public class LessCompBinaryExpr extends CompBinaryExpr {

	public LessCompBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	public Object accept(Visitor v){
		return v.visit(this);
	}

	@Override
	public String toString() {
		
		return super.lhs + " < " + super.rhs + ";";
	}

}
