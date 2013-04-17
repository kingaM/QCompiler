package ast;

import java.util.ArrayList;

public class SeqExpr extends Expr {

	private ArrayList<Expr> sequence;

	public SeqExpr(ArrayList<Expr> sequence) {
		super();
		this.sequence = sequence;
	}

	public ArrayList<Expr> getSequence() {
		return sequence;
	}

	public void setSequence(ArrayList<Expr> sequence) {
		this.sequence = sequence;
	}

	@Override
	public String toString() {
		return "SeqExpr [sequence=" + sequence + "]";
	}

}
