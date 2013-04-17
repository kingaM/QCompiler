package ast;

import java.util.ArrayList;

public class FunctionStmtList {
	ArrayList<Stmt> stmtList;
	VariableDecl vdecl;
	
	
	public FunctionStmtList(ArrayList<Stmt> stmtList, VariableDecl vdecl) {
		super();
		this.stmtList = stmtList;
		this.vdecl = vdecl;
	}
	
	/*not sure about this
	public FunctionStmtList(ArrayList<Stmt> stmtList){
		this.stmtList = stmtList;
	}*/

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
	

}
