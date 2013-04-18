package ast;

public class ReturnStmt extends Stmt{
	private Expr returnExpr;

	public ReturnStmt(Expr returnExpr) {
		super();
		this.returnExpr = returnExpr;
	}

	public Expr getReturnExpr() {
		return returnExpr;
	}

	public void setReturnExpr(Expr returnExpr) {
		this.returnExpr = returnExpr;
	}

	@Override
	public String toString() {
		return "ReturnStmt [returnExpr=" + returnExpr + "]";
	}
}
