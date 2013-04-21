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
	
	public Object accept(Visitor v){
		return v.visit(this);
	}

	@Override
	public String toString() {
		return Boolean.toString(b);
	}

}
