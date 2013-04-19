package ast;

import visitor.Visitor;

public class PowerBinaryExpr extends BinaryExpr {

	public PowerBinaryExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}

	
	public void accept(Visitor v){
		v.visit(this);
	}


}
