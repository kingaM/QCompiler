package ast;

import visitor.Visitor;

public class SeqSlicingExpr extends Expr {
	
	private String sequence;
	private Expr start;
	private Expr finish;
	
	public SeqSlicingExpr(String sequence, Expr start, Expr finish) {
		this.sequence = sequence;
		this.start = start;
		this.finish = finish;
	}

	public String getSequence() {
		return sequence;
	}

	public Expr getStart() {
		return start;
	}

	public Expr getFinish() {
		return finish;
	}
	
	public Object accept(Visitor v){
		return v.visit(this);
	}


	@Override
	public String toString() {
		return "[" + start + ":" + sequence + ":" + finish + "]";
		/*return "SeqSlicingExpr ["
				+ (sequence != null ? "sequence=" + sequence + ", " : "")
				+ (start != null ? "start=" + start + ", " : "")
				+ (finish != null ? "finish=" + finish : "") + "]";*/
	}

	
}
