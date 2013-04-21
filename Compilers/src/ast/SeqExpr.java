package ast;

import java.util.ArrayList;

import visitor.Visitor;

public class SeqExpr extends Expr {

	private ArrayList<Expr> sequence;
	private String type;

	public SeqExpr(ArrayList<Expr> sequence, String type) {
		super();
		this.sequence = sequence;
		this.type = type;
	}

	public ArrayList<Expr> getSequence() {
		return sequence;
	}

	public String getType() {
		return type;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}

	@Override
	public String toString() {
		String toprint = "";
		for(Expr e : sequence){
			toprint = toprint + e;
		}
		return toprint + " : " + type;
		/*return "SeqExpr ["
				+ (sequence != null ? "sequence=" + sequence + ", " : "")
				+ (type != null ? "type=" + type : "") + "]";*/
	}

}
