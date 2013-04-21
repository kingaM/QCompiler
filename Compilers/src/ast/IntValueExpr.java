package ast;

import visitor.Visitor;

public class IntValueExpr extends ValueExpr {

	private int value;

	public IntValueExpr(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	public Object accept(Visitor v){
		return v.visit(this);
	}
	@Override
	public String toString() {
		return "IntValueExpr [value=" + value + "]";
	}

}
