package visitor;

import java.util.ArrayList;

import ast.AndExpr;
import ast.BoolValueExpr;
import ast.CharValueExpr;
import ast.CompBinaryExpr;
import ast.ConcatBinaryExpr;
import ast.Decl;
import ast.DivideBinaryExpr;
import ast.DotBinaryExpr;
import ast.EqBinaryExpr;
import ast.Expr;
import ast.ExprStmt;
import ast.FcallExpr;
import ast.Field;
import ast.FloatValueExpr;
import ast.FunctionDecl;
import ast.FunctionStmtList;
import ast.GreaterCompBinaryExpr;
import ast.GreaterOrEqExpr;
import ast.IfElseStmt;
import ast.IfStmt;
import ast.InExpr;
import ast.IntValueExpr;
import ast.LessCompBinaryExpr;
import ast.LessEqCompBinaryExpr;
import ast.MainDecl;
import ast.MinusBinaryExpr;
import ast.OrExpr;
import ast.PlusBinaryExpr;
import ast.PowerBinaryExpr;
import ast.Program;
import ast.RepeatUntilStmt;
import ast.ReturnStmt;
import ast.SeqCallExpr;
import ast.SeqExpr;
import ast.SeqSlicingExpr;
import ast.Stmt;
import ast.StrValueExpr;
import ast.TimesBinaryExpr;
import ast.TypeDecl;
import ast.ValueExpr;
import ast.VarExpr;
import ast.VarStmt;
import ast.VariableDecl;
import ast.WhileStmt;

public class TypeScopeVisitor implements Visitor {

	private SymbolTable symTab;
	private int numOfErrors = 0;

	public TypeScopeVisitor(SymbolTable st) {
		symTab = st;
	}

	@Override
	public Object visit(Program p) {
		for (int i = 0; i < p.getDecllist().size(); i++) {
			p.getDecllist().get(i).accept(this);
		}
		p.getMain().accept(this);
		return null;
	}

	@Override
	public Object visit(Decl d) {
		return null;
	}

	@Override
	public Object visit(MainDecl d) {
		for (int i = 0; i < d.getStmts().size(); i++) {
			d.getStmts().get(i).accept(this);
		}
		return null;
	}

	@Override
	public Object visit(VariableDecl d) {
		String id = d.getId();
		String type = d.getType();
		for (int i = 0; i < d.getInit().size(); i++) {
			d.getInit().get(i).accept(this);
		}
		return d.getType();
	}

	@Override
	public Object visit(FunctionDecl d) {
		if(symTab.get(d.getId()).getId() != null){
			System.out.println("already there");
		}
		String id = d.getId();
		symTab = symTab.getNextScope();
		//System.out.println("BA " + symTab.get(d.);
		if (d.getFieldDecl() != null) {
			System.out.println("\nEXTRA CHECK: " + d.getFieldDecl().toString());
			for (int i = 0; i < d.getFieldDecl().size(); i++) {
				d.getFieldDecl().get(i).accept(this);
			}
		}
		for (int i = 0; i < d.getBody().size(); i++) {
			d.getBody().get(i).accept(this);
		}
		symTab = symTab.exitScope();
		return null;
	}

	@Override
	public Object visit(TypeDecl d) {
		// TODO Scope Check
		String id = d.getId();
		String structure = getStructure(d.getFields());
		SymbolEntry entry = symTab.get(id);
		if ((entry == null) || !entry.getType().equals(structure)) {
			return printError("error");
		}
		for (int i = 0; i < d.getFields().size(); i++) {
			d.getFields().get(i).accept(this);
		}
		return null;
	}

	@Override
	public Object visit(FunctionStmtList l) {
		for (int i = 0; i < l.getStmtList().size(); i++) {
			l.getStmtList().get(i).accept(this);
		}
		return null;
	}

	@Override
	public Object visit(Field f) {
		String type = f.getType();
		return type;
	}

	@Override
	public Object visit(Expr e) {
		return null;
	}

	@Override
	public Object visit(AndExpr e) {
		String typel = (String) e.getLhs().accept(this);
		String typer = (String) e.getRhs().accept(this);
		return checkBool(typel, typer);
	}

	private String checkBool(String l, String r) {
		if (l == "bool" && r == "bool")
			return "bool";
		else
			return printError("error");
	}

	private boolean isCompatible(String l, String r) {
		if ((l.equals("int") && r.equals("float"))
				|| (l.equals("int") && r.equals("int"))
				|| (l.equals("float") && r.equals("float"))
				|| (l.equals("float") && r.equals("int"))
				|| (l.equals("bool") && r.equals("bool"))
				|| (l.equals("int") && r.equals("bool"))
				|| (l.equals("bool") && r.equals("int")))
			return true;
		return false;
	}

	private String getSignature(ArrayList<String> fields) {

		if (fields != null) {
			String sig = "";
			for (int i = 0; i < fields.size(); i++) {
				if (i > 0)
					sig = sig + ";" + fields.get(i);
				else
					sig = sig + fields.get(i);
			}
			return sig;
		}

		return "void";
	}

	private String getStructure(ArrayList<Field> fieldDecl) {

		if (fieldDecl != null) {
			String sig = "";
			for (int i = 0; i < fieldDecl.size(); i++) {
				if (i > 0)
					sig = sig + ";" + fieldDecl.get(i).getId() + ":"
							+ fieldDecl.get(i).getType();
				else
					sig = sig + fieldDecl.get(i).getType() + ":"
							+ fieldDecl.get(i).getId();
			}
			return sig;
		}

		return "void";
	}

	@Override
	public Object visit(BoolValueExpr e) {
		return "bool";
	}

	@Override
	public Object visit(CharValueExpr e) {
		return "char";
	}

	@Override
	public Object visit(CompBinaryExpr e) {
		String typel = (String) e.getLhs().accept(this);
		String typer = (String) e.getRhs().accept(this);
		if (isCompatible(typel, typer))
			return "bool";
		return printError("error");

	}

	@Override
	public Object visit(ConcatBinaryExpr e) {
		String typel = (String) e.getLhs().accept(this);
		String typer = (String) e.getRhs().accept(this);
		if (typel.equals("list") && typer.equals("list"))
			return "list";
		else if (typel.equals("tuple") && typer.equals("tuple"))
			return "tuple";
		else if (typel.equals("string") && typer.equals("string"))
			return "string";
		return printError("error");
	}

	@Override
	public Object visit(DivideBinaryExpr e) {
		String typel = (String) e.getLhs().accept(this);
		String typer = (String) e.getRhs().accept(this);
		return typeOperator(typel, typer);
	}

	private String typeOperator(String l, String r) {
		if ((l.equals("int") && r.equals("int"))
				|| (l.equals("int") && r.equals("float"))
				|| (l.equals("float") && r.equals("int")))
			return "int";
		if (l.equals("float") && r.equals("float"))
			return "float";
		return printError("error");
	}

	@Override
	public Object visit(DotBinaryExpr e) {
		// TODO Scope and type check
		return null;
	}

	@Override
	public Object visit(EqBinaryExpr e) {
		String typel = (String) e.getLhs().accept(this);
		String typer = (String) e.getRhs().accept(this);
		return checkBool(typel, typer);
	}

	@Override
	public Object visit(ExprStmt e) {
		return e.getE().accept(this);
		// return null;
	}

	@Override
	public Object visit(FcallExpr e) {
		String id = e.getId();
		ArrayList<String> fields = new ArrayList<String>();
		for (int i = 0; i < e.getParameters().size(); i++) {
			fields.add((String) e.getParameters().get(i).accept(this));
		}

		String callType = getSignature(fields);
		SymbolEntry entry = symTab.get(id);
		if (entry != null && entry.getType() == SymbolType.FDEF
				&& entry.getVarType().equals(callType))
			return entry.getRetType();
		if (id.equals("len"))
			return "int";
		return printError("error");
	}

	@Override
	public Object visit(FloatValueExpr e) {
		return "float";
	}

	@Override
	public Object visit(GreaterCompBinaryExpr e) {
		String typel = (String) e.getLhs().accept(this);
		String typer = (String) e.getRhs().accept(this);
		if (isCompatible(typel, typer))
			return "bool";
		return printError("error");
	}

	@Override
	public Object visit(GreaterOrEqExpr e) {
		String typel = (String) e.getLhs().accept(this);
		String typer = (String) e.getRhs().accept(this);
		if (isCompatible(typel, typer))
			return "bool";
		return printError("error");
	}

	@Override
	public Object visit(InExpr e) {
		e.getLhs().accept(this);
		String type = (String) e.getRhs().accept(this);
		if (type.equals("list") || type.equals("tuple")
				|| type.equals("string"))
			return "bool";
		return printError("error");
	}

	@Override
	public Object visit(IntValueExpr e) {
		return "int";
	}

	@Override
	public Object visit(LessCompBinaryExpr e) {
		String typel = (String) e.getLhs().accept(this);
		String typer = (String) e.getRhs().accept(this);
		if (isCompatible(typel, typer))
			return "bool";
		return printError("error");
	}

	@Override
	public Object visit(LessEqCompBinaryExpr e) {
		String typel = (String) e.getLhs().accept(this);
		String typer = (String) e.getRhs().accept(this);
		if (isCompatible(typel, typer))
			return "bool";
		return printError("error");
	}

	@Override
	public Object visit(MinusBinaryExpr e) {
		String typel = (String) e.getLhs().accept(this);
		String typer = (String) e.getRhs().accept(this);
		return typeOperator(typel, typer);
	}

	@Override
	public Object visit(OrExpr e) {
		String typel = (String) e.getLhs().accept(this);
		String typer = (String) e.getRhs().accept(this);
		return checkBool(typel, typer);
	}

	@Override
	public Object visit(PlusBinaryExpr e) {
		String typel = (String) e.getLhs().accept(this);
		String typer = (String) e.getRhs().accept(this);
		System.out.println("L: " + typel + " R: " + typer);
		return typeOperator(typel, typer);
	}

	@Override
	public Object visit(PowerBinaryExpr e) {
		String typel = (String) e.getLhs().accept(this);
		String typer = (String) e.getRhs().accept(this);
		return typeOperator(typel, typer);
	}

	@Override
	public Object visit(SeqCallExpr e) {
		// TODO sth is wrong here with the tuple type...
		String id = (String) e.getId().accept(this);
		String typer = (String) e.getCall().accept(this);
		SymbolEntry entry = symTab.get(id);
		if (entry != null
				&& (entry.getType() == SymbolType.ARG
						|| entry.getType() == SymbolType.VAR || entry.getType() == SymbolType.TDEF)
				&& typer.equals("int"))
			return entry.getType();
		return printError("error");
	}

	@Override
	public Object visit(SeqExpr e) {

		if (e.getType().equals("list")) {
			// TODO type is null if it is a sq
			String type = (String) e.getSequence().get(0).accept(this);
			System.out.println("TYPE: " + type);
			for (int i = 1; i < e.getSequence().size(); i++) {
				if (!type.equals((String) e.getSequence().get(i).accept(this)))
					return printError("error");
			}
			return "list";
		}
		if (e.getType().equals("tuple")) {
			for (int i = 0; i < e.getSequence().size(); i++) {
				e.getSequence().get(i).accept(this);
			}
			return "tuple";
		}
		return printError("error");
	}

	@Override
	public Object visit(SeqSlicingExpr e) {
		String seqType = (String) e.getSequence().accept(this);
		Expr start = e.getStart();
		Expr finish = e.getFinish();
		if ((start != null && start.accept(this).equals("int"))
				|| (finish != null && finish.accept(this).equals("int"))
				|| (start == null && finish == null))
			return seqType;
		return printError("error");
	}

	@Override
	public Object visit(StrValueExpr e) {
		return "string";
	}

	@Override
	public Object visit(TimesBinaryExpr e) {
		String typel = (String) e.getLhs().accept(this);
		String typer = (String) e.getRhs().accept(this);
		return typeOperator(typel, typer);
	}

	@Override
	public Object visit(ValueExpr e) {
		return null;
	}

	@Override
	public Object visit(VarExpr e) {
		SymbolEntry entry = symTab.get(e.getVar());
		System.out.println("Entry: " + entry);
		if (entry != null
				&& (entry.getType() == SymbolType.VAR
						|| entry.getType() == SymbolType.ARG || entry.getType() == SymbolType.TDEF))
			return entry.getVarType();
		return printError("error");
	}

	@Override
	public Object visit(Stmt s) {
		return null;
	}

	@Override
	public Object visit(ReturnStmt s) {
		return s.getReturnExpr().accept(this);
	}

	@Override
	public Object visit(IfStmt s) {

		if (!s.getCondition().accept(this).equals("bool"))
			return printError("error");
		symTab = symTab.getNextScope();
		for (int i = 0; i < s.getBody().size(); i++) {
			s.getBody().get(i).accept(this);
		}
		symTab = symTab.exitScope();
		return null;
	}

	@Override
	public Object visit(RepeatUntilStmt s) {
		if (!s.getCondition().accept(this).equals("bool"))
			return printError("error");
		symTab = symTab.getNextScope();
		for (int i = 0; i < s.getBody().size(); i++) {
			s.getBody().get(i).accept(this);
		}
		symTab = symTab.exitScope();
		return null;
	}

	@Override
	public Object visit(IfElseStmt s) {
		if (!s.getCondition().accept(this).equals("bool"))
			return printError("error");
		symTab = symTab.getNextScope();
		for (int i = 0; i < s.getIfBody().size(); i++) {
			s.getIfBody().get(i).accept(this);
		}
		symTab = symTab.exitScope();
		symTab = symTab.getNextScope();
		for (int i = 0; i < s.getElseBody().size(); i++) {
			s.getElseBody().get(i).accept(this);
		}
		symTab = symTab.exitScope();
		return null;
	}

	@Override
	public Object visit(VarStmt s) {
		s.getVarDecl().accept(this);
		return null;
	}

	@Override
	public Object visit(WhileStmt s) {
		if (!s.getCondition().accept(this).equals("bool"))
			return printError("error");
		symTab = symTab.getNextScope();
		for (int i = 0; i < s.getBody().size(); i++) {
			s.getBody().get(i).accept(this);
		}
		symTab = symTab.exitScope();
		return null;
	}

	private String printError(String error) {
		System.err.println(error);
		numOfErrors++;
		return "error";
	}

	public int getNumOfErrors() {
		return numOfErrors;
	}
}
