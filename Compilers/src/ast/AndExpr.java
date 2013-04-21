package ast;

import visitor.Visitor;

public class AndExpr extends BinaryExpr{

	public AndExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
		
	public Object accept(Visitor v){
		return v.visit(this);
	}

	@Override
	public String toString() {
		
		return super.lhs + " && " + super.rhs + ";";
	}
}
