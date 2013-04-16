package ast;

public class VariableDecl extends Decl {
	
	private String id;
	private String type;
	private Expr init;
	
	public VariableDecl(String id, String type, Expr seq) {
		super();
		this.id = id;
		this.type = type;
		this.init = seq;
	}

	@Override
	public String toString() {
		return "VariableDecl [id=" + id + ", type=" + type + ", init=" + init
				+ "]";
	}

}
