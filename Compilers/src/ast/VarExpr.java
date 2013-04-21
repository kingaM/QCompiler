package ast;

import visitor.Visitor;

public class VarExpr extends Expr {

	private String var;

	public VarExpr(String var) {
		super();
		this.var = var;
	}

	public String getVar() {
		return var;
	}
	
	public Object accept(Visitor v){
		return v.visit(this);
	}


	@Override
	public String toString() {
		return "VarExpr [" + (var != null ? "var=" + var : "") + "]";
	}
	
	
}
