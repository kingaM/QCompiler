package ast;

public class SeqExpr extends Expr {

	private Expr sequence;
	private Expr index;
	
	public SeqExpr(Expr sequence, Expr index) {
		this.sequence = sequence;
		this.index = index;
	}

	public Expr getSequence() {
		return sequence;
	}

	public Expr getIndex() {
		return index;
	}

	@Override
	public String toString() {
		return "SeqExpr ["
				+ (sequence != null ? "sequence=" + sequence + ", " : "")
				+ (index != null ? "index=" + index : "") + "]";
	}
	
}
