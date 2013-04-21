package ast;

import java.util.ArrayList;

import visitor.Visitor;

public class FunctionStmtList {
	ArrayList<Stmt> stmtList;
	VariableDecl vdecl;
	
	
	public FunctionStmtList(ArrayList<Stmt> stmtList, VariableDecl vdecl) {
		super();
		this.stmtList = stmtList;
		this.vdecl = vdecl;
	}


	public ArrayList<Stmt> getStmtList() {
		return stmtList;
	}

	public void setStmtList(ArrayList<Stmt> stmtList) {
		this.stmtList = stmtList;
	}

	public VariableDecl getVdecl() {
		return vdecl;
	}

	public void setVdecl(VariableDecl vdecl) {
		this.vdecl = vdecl;
	}
	

    public Object accept(Visitor v){
		return v.visit(this);
	}

}
