package ast;

public class CharValueExpr extends ValueExpr {

	private char c;

	public CharValueExpr(char c) {
		this.c = c;
	}

	public char getC() {
		return c;
	}

	@Override
	public String toString() {
		return "CharValueExpr [c=" + c + "]";
	}

}
