package ast;

import visitor.Visitor;

public class BoolValueExpr extends ValueExpr {

	private boolean b;

	public BoolValueExpr(boolean b) {
		this.b = b;
	}

	public boolean isB() {
		return b;
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}

	@Override
	public String toString() {
		return "BoolValueExpr [b=" + b + "]";
	}

}
