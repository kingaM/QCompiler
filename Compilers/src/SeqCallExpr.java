import ast.Expr;


public class SeqCallExpr extends Expr {
	
	private Expr id;
	private Expr call;
	
	public SeqCallExpr(Expr id, Expr call) {
		this.id = id;
		this.call = call;
	}
	
	public Expr getId() {
		return id;
	}
	
	public Expr getCall() {
		return call;
	}
	
	@Override
	public String toString() {
		return "SeqCallExpr [" + (id != null ? "id=" + id + ", " : "")
				+ (call != null ? "call=" + call : "") + "]";
	}

}
