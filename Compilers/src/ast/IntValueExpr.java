package ast;

public class IntValueExpr extends ValueExpr {
	
	private int value;

	public IntValueExpr(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "IntValueExpr [value=" + value + "]";
	}

}
