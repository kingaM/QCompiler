package ast;

import visitor.Visitor;

public class BinaryExpr extends Expr {
	
	private Expr lhs;
	private Expr rhs;
	
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

	@Override
	public String toString() {
		return "BinaryExpr [" + (lhs != null ? "lhs=" + lhs + ", " : "")
				+ (rhs != null ? "rhs=" + rhs : "") + "]";
	}

}
