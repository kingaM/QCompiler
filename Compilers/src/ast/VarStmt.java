package ast;

import visitor.Visitor;

public class VarStmt extends Stmt {
	
	private VariableDecl varDecl;

	public VarStmt(Decl vd) {
		this.varDecl = (VariableDecl) vd;
	}

	public VariableDecl getVarDecl() {
		return varDecl;
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}

	@Override
	public String toString() {
		return "VarStmt [" + (varDecl != null ? "varDecl=" + varDecl : "")
				+ "]";
	}

	

}
