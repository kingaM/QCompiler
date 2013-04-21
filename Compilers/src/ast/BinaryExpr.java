package ast;

import visitor.Visitor;

public class BinaryExpr extends Expr {
	
	protected Expr lhs;
	protected Expr rhs;
	
	public BinaryExpr(Expr lhs, Expr rhs) {
		super();
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public Expr getLhs() {
		return lhs;
	}

	public Expr getRhs() {
		return rhs;
	}
	
	public Object accept(Visitor v){
		return v.visit(this);
	}


}
