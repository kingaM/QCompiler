package ast;

import visitor.Visitor;

public class DotBinaryExpr extends Expr {
	
	private String lhs;
	private String rhs;

	public DotBinaryExpr(String lhs, String rhs) {
		super();
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	
	
	public Object accept(Visitor v){
		return v.visit(this);
	}


	public String getLhs() {
		return lhs;
	}


	public String getRhs() {
		return rhs;
	}

	@Override
	public String toString() {
		
		return lhs + "." + rhs + ";";
	}
}
