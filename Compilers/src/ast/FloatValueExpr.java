package ast;

import visitor.Visitor;

public class FloatValueExpr extends ValueExpr {
	
	private float value;

	public FloatValueExpr(float value) {
		this.value = value;
	}

	public float getValue() {
		return value;
	}
	
	public Object accept(Visitor v){
		return v.visit(this);
	}


	@Override
	public String toString() {
		return Float.toString(value);
	}

}
