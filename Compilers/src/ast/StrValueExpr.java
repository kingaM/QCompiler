package ast;

public class StrValueExpr extends ValueExpr {

	private String value;

	public StrValueExpr(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "StrValueExpr [" + (value != null ? "value=" + value : "") + "]";
	}

}
