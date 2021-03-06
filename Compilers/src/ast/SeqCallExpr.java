package ast;

import visitor.Visitor;


public class SeqCallExpr extends Expr {
	
	private String id;
	private Expr call;
	
	public SeqCallExpr(String id, Expr call) {
		this.id = id;
		this.call = call;
	}
	
	public String getId() {
		return id;
	}
	
	public Expr getCall() {
		return call;
	}
	
	public Object accept(Visitor v){
		return v.visit(this);
	}


	@Override
	public String toString() {
		return id + "[" + call + "]";
		/*return "SeqCallExpr [" + (id != null ? "id=" + id + ", " : "")
				+ (call != null ? "call=" + call : "") + "]";*/
	}

}
