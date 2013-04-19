package ast;

import visitor.Visitor;

public class CharValueExpr extends ValueExpr {

	private char c;

	public CharValueExpr(char c) {
		this.c = c;
	}

	public char getC() {
		return c;
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}

	@Override
	public String toString() {
		return "CharValueExpr [c=" + c + "]";
	}

}
