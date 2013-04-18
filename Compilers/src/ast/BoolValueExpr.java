package ast;

public class BoolValueExpr extends ValueExpr {

	private boolean b;

	public BoolValueExpr(boolean b) {
		this.b = b;
	}

	public boolean isB() {
		return b;
	}

	@Override
	public String toString() {
		return "BoolValueExpr [b=" + b + "]";
	}

}
