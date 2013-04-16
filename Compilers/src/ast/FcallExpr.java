package ast;

public class FcallExpr extends Expr {
	
	private String id;
	private Expr parameters;
	
	public FcallExpr(String id, Expr parameters) {
		this.id = id;
		this.parameters = parameters;
	}

	public String getId() {
		return id;
	}

	public Expr getParameters() {
		return parameters;
	}

	@Override
	public String toString() {
		return "FcallExpr [id=" + id + ", parameters=" + parameters + "]";
	}
	
	

}
