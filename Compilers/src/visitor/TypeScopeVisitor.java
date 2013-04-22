package visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import static java.util.Arrays.asList;

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
import ast.NotUnaryExpr;
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

	private ErrorHandler eh;
	
	private List<String> basicTypes = new ArrayList<String>(asList("int", "float", "char", "bool", "string", "list", "tuple"));

	public TypeScopeVisitor(SymbolTable st, ErrorHandler eh) {
		symTab = st;
		this.eh = eh;
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
		symTab = symTab.getNextScope();
		for (int i = 0; i < d.getStmts().size(); i++) {
			d.getStmts().get(i).accept(this);
		}
		symTab = symTab.exitScope();
		return null;
	}

	@Override
	public Object visit(VariableDecl d) {
		String id = d.getId();
		String type = d.getType();
		if(d.getInit()!=null){
			if(basicTypes.contains(type))
			for (int i = 0; i < d.getInit().size(); i++) {
				String test = ( String) d.getInit().get(i).accept(this);
				if(! type.equals(test) && !(type.equals("float") && test.equals("int")) 
						&& !(type.equals("int") && test.equals("bool"))
						&& !(type.equals("string") && test.equals("char"))
						){
					eh.printErrorMessage(test,
							"incompatible types for assigment", ErrorHandler.ErrorType.TYPE);
					return "error";
				}
			}
			
			else {
				SymbolEntry entry = symTab.get(type);
				if (entry == null) {
					eh.printErrorMessage(id, "unknown type",
							ErrorHandler.ErrorType.SCOPE_NOTDECL);
					return "error";
				}
				StringTokenizer st = new StringTokenizer(entry.getVarType(), ";");
				for (int i = 0; i < d.getInit().size(); i++) {
					String test = ( String) d.getInit().get(i).accept(this);
					if(test == null ) test = "string";
					if(st.hasMoreTokens()) {
						String s = st.nextToken();
						if (!s.contains(test)) {
							eh.printErrorMessage(id, "type mismatch for fields",
									ErrorHandler.ErrorType.SCOPE_NOTDECL);
							return "error";
						}
					}
					else {
					eh.printErrorMessage(id, "type mismatch for fields",
							ErrorHandler.ErrorType.SCOPE_NOTDECL);
					return "error";
					}
					
				}
				
			}
			
		}
		return d.getType();
	}
	

	@Override
	public Object visit(FunctionDecl d) {
		symTab = symTab.getNextScope();
		if (d.getFieldDecl() != null) {
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
		String id = d.getId();
		String structure = getStructure(d.getFields());
		SymbolEntry entry = symTab.get(id);

		if (entry == null) {
			eh.printErrorMessage(id, "type declaration",
					ErrorHandler.ErrorType.SCOPE_NOTDECL);
			return "error";
		}
		if (!entry.getVarType().equals(structure)) {
			eh.printErrorMessage((String) entry.getVarType(),
					"type declaration", ErrorHandler.ErrorType.TYPE);
			return "error";
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
		if (l.equals("bool") && r.equals("bool"))
			return "bool";
		eh.printErrorMessage(l + " " + r, "comparable expression",
				ErrorHandler.ErrorType.TYPE);
		return "error";
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
					sig = sig + ";" + fieldDecl.get(i).getType() + ":"
							+ fieldDecl.get(i).getId();
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
		eh.printErrorMessage(typel + " " + typer, "comparable expression",
				ErrorHandler.ErrorType.TYPE);
		return "error";

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

		eh.printErrorMessage(typel + " " + typer, "concatination",
				ErrorHandler.ErrorType.TYPE);
		return "error";
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

		eh.printErrorMessage(l + " " + r, "binary operator expression",
				ErrorHandler.ErrorType.TYPE);
		return "error";
	}

	@Override
	public Object visit(DotBinaryExpr e) {
		String id = e.getLhs();
		String field = e.getRhs();
		SymbolEntry entry = symTab.get(id);

		if (entry == null) {
			eh.printErrorMessage(id, "user defined type accessor",
					ErrorHandler.ErrorType.SCOPE_NOTDECL);
			return "error";
		}

		else {
			String type = entry.getVarType();
			SymbolEntry userType = symTab.get(type);

			StringTokenizer st = new StringTokenizer(userType.getVarType(), ";");
			while (st.hasMoreTokens()) {
				String test = st.nextToken();
				System.out.println(test);
				if (test.contains(field)) {
					StringTokenizer st1 = new StringTokenizer(test, ":");
					return st1.nextToken();
				}
			}
		
		eh.printErrorMessage(id, "wrong user defined type accessor",
				ErrorHandler.ErrorType.SCOPE_NOTDECL);
		return "error";
		}

	}

	@Override
	public Object visit(EqBinaryExpr e) {
		String typel = (String) e.getLhs().accept(this);
		String typer = (String) e.getRhs().accept(this);

		if (typel.equals(typer))
			return typel;
		if (typel.equals("float") && typer.equals("int"))
			return "float";
		eh.printErrorMessage(typel + " " + typer, "assingment",
				ErrorHandler.ErrorType.TYPE);
		return "error";
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
		if (id.equals("len")
				&& fields.size() == 1
				&& (fields.get(0).equals("list")
						|| fields.get(0).equals("tuple") || fields.get(0)
						.equals("string")))
			return "int";

		String fparameters = "";
		for (int j = 0; j < fields.size() - 1; j++) {
			fparameters = fields.get(j) + ";";
		}
		fparameters = fparameters + fields.get(fields.size() - 1);
		String fcallId = e.getId() + ">" + fparameters;

		if (symTab.get(fcallId) != null) {
			return symTab.get(fcallId).getRetType();
		}
		if (fparameters.equals("int;int")) {
			if (symTab.get(e.getId() + ">float;int") != null)
				return symTab.get(e.getId() + ">float;int").getRetType();
			if (symTab.get(e.getId() + ">int;float") != null)
				return symTab.get(e.getId() + ">int;float").getRetType();
			if (symTab.get(e.getId() + ">float;float") != null) {
				return symTab.get(e.getId() + ">float;float").getRetType();
			}
		}
		if (fparameters.equals("float;int") || fparameters.equals("int;float")) {
			if (symTab.get(e.getId() + ">float;float") != null) {
				return symTab.get(e.getId() + ">float;float").getRetType();

			}
		}
		if (fparameters.equals("char;char"))
			if (symTab.get(">string;string") != null)
				return symTab.get(">string;string").getRetType();
		if (fparameters.equals("bool;bool"))
			if (symTab.get(">int;int") != null)
				return symTab.get(">int;int").getRetType();
		eh.printErrorMessage(id, "function call", ErrorHandler.ErrorType.TYPE);
		return "error";
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

		eh.printErrorMessage(typel + " " + typer, "greater than expression",
				ErrorHandler.ErrorType.TYPE);
		return "error";
	}

	@Override
	public Object visit(GreaterOrEqExpr e) {
		String typel = (String) e.getLhs().accept(this);
		String typer = (String) e.getRhs().accept(this);
		if (isCompatible(typel, typer))
			return "bool";

		eh.printErrorMessage(typel + " " + typer,
				"greater or equal expression", ErrorHandler.ErrorType.TYPE);
		return "error";
	}

	@Override
	public Object visit(InExpr e) {
		e.getLhs().accept(this);
		String type = (String) e.getRhs().accept(this);
		if (type.equals("list") || type.equals("tuple")
				|| type.equals("string") || type == null)
			return "bool";

		eh.printErrorMessage(type, "in expression", ErrorHandler.ErrorType.TYPE);
		return "error";
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

		eh.printErrorMessage(typel + " " + typer, "less than expression",
				ErrorHandler.ErrorType.TYPE);
		return "error";
	}

	@Override
	public Object visit(LessEqCompBinaryExpr e) {
		String typel = (String) e.getLhs().accept(this);
		String typer = (String) e.getRhs().accept(this);
		if (isCompatible(typel, typer))
			return "bool";

		eh.printErrorMessage(typel + " " + typer,
				"less than or equal expression", ErrorHandler.ErrorType.TYPE);
		return "error";
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

		String id = (String) e.getId();
		String typer = (String) e.getCall().accept(this);
		SymbolEntry entry = symTab.get(id);
		if (entry == null) {
			eh.printErrorMessage(id, "sequence call",
					ErrorHandler.ErrorType.SCOPE_NOTDECL);
			return "error";
		}
		if (entry != null
				&& (entry.getType() == SymbolType.ARG || entry.getType() == SymbolType.VAR)
				&& typer.equals("int"))
			return entry.getType();

		eh.printErrorMessage(typer, "sequence call",
				ErrorHandler.ErrorType.TYPE);
		return "error";
	}

	@Override
	public Object visit(SeqExpr e) {
		if (e.getType().equals("list")) {
			// TODO type is null if it is a string
			if (e.getSequence() != null) {
				String type = (String) e.getSequence().get(0).accept(this);
				for (int i = 1; i < e.getSequence().size(); i++) {

					String typeToCheck = (String) e.getSequence().get(i)
							.accept(this);
					if (!type.equals(typeToCheck))
						eh.printErrorMessage(typeToCheck,
								"sequence defninition",
								ErrorHandler.ErrorType.TYPE);
					return "error";
				}
			}
			return "list";
		}
		if (e.getType().equals("tuple")) {
			if (e.getSequence() != null)
				for (int i = 0; i < e.getSequence().size(); i++) {
					e.getSequence().get(i).accept(this);
				}
			return "tuple";
		}

		eh.printErrorMessage(e.getType(), "sequence defninition",
				ErrorHandler.ErrorType.TYPE);
		return "error";
	}

	@Override
	public Object visit(SeqSlicingExpr e) {

		String seqType = (String) e.getSequence();
		if (seqType.equals("tuple") || seqType.equals("string")
				|| seqType.equals("list")) {

			Expr start = e.getStart();
			Expr finish = e.getFinish();
			if ((start != null && start.accept(this).equals("int"))
					|| (finish != null && finish.accept(this).equals("int"))
					|| (start == null && finish == null))
				return seqType;

		}
		eh.printErrorMessage(seqType, "sequence slicing",
				ErrorHandler.ErrorType.TYPE);
		return "error";
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
		if (entry != null
				&& (entry.getType() == SymbolType.VAR || entry.getType() == SymbolType.ARG))
			return entry.getVarType();

		eh.printErrorMessage(e.getVar(), "variable referencing",
				ErrorHandler.ErrorType.SCOPE_NOTDECL);
		return "error";
	}

	@Override
	public Object visit(Stmt s) {
		return null;
	}

	@Override
	public Object visit(ReturnStmt s) {
		SymbolEntry e = symTab.get("_FunctReturnType");
		String type = (String) s.getReturnExpr().accept(this);
		if (type.equals(e.getVarType())) {
			return type;
		}
		if (type.equals("int") && e.getVarType().equals("float"))
			return "float";
		if (type.equals("char") && e.getVarType().equals("string"))
			return "string";
		if (type.equals("bool") && e.getVarType().equals("int"))
			return "int";
		eh.printErrorMessage(type, "return statement",
				ErrorHandler.ErrorType.TYPE);
		return "error";

	}

	@Override
	public Object visit(IfStmt s) {

		String type = (String) s.getCondition().accept(this);
		if (!type.equals("bool")) {
			eh.printErrorMessage(type, "condition of a statement",
					ErrorHandler.ErrorType.TYPE);
			return "error";
		}
		symTab = symTab.getNextScope();
		if (s.getBody() != null)
			for (int i = 0; i < s.getBody().size(); i++) {
				s.getBody().get(i).accept(this);
			}
		symTab = symTab.exitScope();
		return null;
	}

	@Override
	public Object visit(RepeatUntilStmt s) {
		String type = (String) s.getCondition().accept(this);
		if (!type.equals("bool")) {
			eh.printErrorMessage(type, "condition of a statement",
					ErrorHandler.ErrorType.TYPE);
			return "error";
		}
		symTab = symTab.getNextScope();
		if (s.getBody() != null)
			for (int i = 0; i < s.getBody().size(); i++) {
				s.getBody().get(i).accept(this);
			}
		symTab = symTab.exitScope();
		return null;
	}

	@Override
	public Object visit(IfElseStmt s) {

		String type = (String) s.getCondition().accept(this);
		if (!type.equals("bool")) {
			eh.printErrorMessage(type, "condition of a statement",
					ErrorHandler.ErrorType.TYPE);
			return "error";
		}
		symTab = symTab.getNextScope();
		if (s.getIfBody() != null)
			for (int i = 0; i < s.getIfBody().size(); i++) {
				s.getIfBody().get(i).accept(this);
			}
		symTab = symTab.exitScope();
		symTab = symTab.getNextScope();
		if (s.getElseBody() != null)
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

		String type = (String) s.getCondition().accept(this);
		if (!type.equals("bool")) {
			eh.printErrorMessage(type, "condition of a statement",
					ErrorHandler.ErrorType.TYPE);
			return "error";
		}
		symTab = symTab.getNextScope();
		if (s.getBody() != null)
			for (int i = 0; i < s.getBody().size(); i++) {
				s.getBody().get(i).accept(this);
			}
		symTab = symTab.exitScope();
		return null;
	}

	@Override
	public Object visit(NotUnaryExpr e) {
		String type = (String) e.getExpr().accept(this);
		if (type.equals("bool"))
			return "bool";
		eh.printErrorMessage(type, "not expression",
				ErrorHandler.ErrorType.TYPE);
		return "error";
	}
}
