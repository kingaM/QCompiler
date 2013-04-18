package ast;

public class FloatValueExpr extends ValueExpr {
	
	private float value;

	public FloatValueExpr(float value) {
		this.value = value;
	}

	public float getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "FloatValueExpr [value=" + value + "]";
	}

}
