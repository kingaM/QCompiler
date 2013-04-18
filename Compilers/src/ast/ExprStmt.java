package ast;

public class ExprStmt extends Stmt {
	
	private Expr e;

	public ExprStmt(Expr e) {
		this.e = e;
	}
	
	public Expr getE() {
		return e;
	}

	@Override
	public String toString() {
		return "ExprStmt [" + (e != null ? "e=" + e : "") + "]";
	}

}
