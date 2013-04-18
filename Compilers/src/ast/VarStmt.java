package ast;

public class VarStmt extends Stmt {
	
	private VariableDecl varDecl;

	public VarStmt(Decl vd) {
		this.varDecl = (VariableDecl) vd;
	}

	public VariableDecl getVarDecl() {
		return varDecl;
	}

	@Override
	public String toString() {
		return "VarStmt [" + (varDecl != null ? "varDecl=" + varDecl : "")
				+ "]";
	}

	

}
