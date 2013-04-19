package ast;

import visitor.Visitor;


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
	
	public void accept(Visitor v){
		v.visit(this);
	}


	@Override
	public String toString() {
		return "SeqCallExpr [" + (id != null ? "id=" + id + ", " : "")
				+ (call != null ? "call=" + call : "") + "]";
	}

}
