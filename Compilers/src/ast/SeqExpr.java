package ast;

import java.util.ArrayList;

import visitor.Visitor;

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
	
	public void accept(Visitor v){
		v.visit(this);
	}


	@Override
	public String toString() {
		return "SeqExpr [sequence=" + sequence + "]";
	}

}
