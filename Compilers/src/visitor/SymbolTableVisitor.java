package visitor;

import java.util.ArrayList;

import ast.*;

public class SymbolTableVisitor implements Visitor {

	private SymbolTable symTab;
	private ErrorHandler eh;

	public SymbolTableVisitor(SymbolTable st, ErrorHandler eh) {
		symTab = st;
		this.eh = eh;
	}

	@Override
	public Object visit(Program p) {
		System.out.println("Entering program");
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

		System.out.println("\nMain decl");		
		symTab = symTab.enterScope(); 
		for ( int i = 0; i < d.getStmts().size(); i++ ) {
	        d.getStmts().get(i).accept(this);
	    }	
		symTab = symTab.exitScope(); 
		System.out.println("Main decl end");	

		return null;
	}

	@Override
	public Object visit(VariableDecl d) {
		//has id type
		System.out.println("\nVariable decl: " + d.toString());
		String id = d.getId();
		String type = d.getType();
		symTab.put(id, SymbolType.VAR, type);
		for ( int i = 0; i < d.getInit().size(); i++ ) {
	        d.getInit().get(i).accept(this);
	    }	
		System.out.println("Variable decl end");

		return null;
	}

	@Override
	public Object visit(FunctionDecl d) {
		System.out.println("\nFunction decl: " + d.toString());
		//String id = d.getId();
		String signature = getSignature(d.getFieldDecl());
		String id = d.getId() + ">" + signature;
		System.out.println(id);
		//d.setId(id); //is this actually useful? if not delete setId in the ast class too
		symTab.put(id, SymbolType.FDEF, signature, d.getReturnType());
		symTab = symTab.enterScope();
		symTab.put("_FunctReturnType", SymbolType.FDEF, d.getReturnType()); 
		if (d.getFieldDecl() != null) {
			for (int i = 0; i < d.getFieldDecl().size(); i++) {
				d.getFieldDecl().get(i).accept(this);
			}
		}

		for ( int i = 0; i < d.getBody().size(); i++ ) {
			d.getBody().get(i).accept(this);
		}		
		symTab = symTab.exitScope(); 
		System.out.println("Function decl end");

		return null;
	}

	private String getSignature(ArrayList<Field> fieldDecl) {

		if (fieldDecl != null) {
			String sig = "";
			for (int i = 0; i < fieldDecl.size(); i++) {
				if (i > 0)
					sig = sig + ";" + fieldDecl.get(i).getType();
				else
					sig = sig + fieldDecl.get(i).getType();
			}
			return sig;
		}

		return "void";
	}

	private String getStructure(ArrayList<Field> fieldDecl) {

		if (fieldDecl != null) {
			String sig = "";
			for ( int i = 0; i < fieldDecl.size(); i++ ) {
				if(i>0)sig = sig + ";" + fieldDecl.get(i).getType() + ":" + fieldDecl.get(i).getId();
				else sig = sig + fieldDecl.get(i).getType() + ":" + fieldDecl.get(i).getId();
			}
			return sig;
		}

		return "void";
	}

	@Override
	public Object visit(TypeDecl d) {
		System.out.println("Type decl: " + d.toString());	
		String id = d.getId();
		String structure = getStructure(d.getFields());
		symTab.put(id, SymbolType.TDEF, structure);
		System.out.println("Type decl end");
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
		symTab.put(f.getId(), SymbolType.ARG, f.getType());
		return null;
	}

	@Override
	public Object visit(Expr e) {
		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public Object visit(AndExpr e) {
		e.getLhs().accept(this);
		e.getRhs().accept(this);
		System.out.println("And expression: " + e.toString());
		return null;

	}

	@Override
	public Object visit(BoolValueExpr e) {
		System.out.println("Bool expression: " + e.toString());
		return null;

	}

	@Override
	public Object visit(CharValueExpr e) {
		System.out.println("Char Value expression: " + e.toString());
		return null;

	}

	@Override
	public Object visit(CompBinaryExpr e) {
		e.getLhs().accept(this);
		e.getRhs().accept(this);
		System.out.println("Comp expression: " + e.toString());
		return null;

	}

	@Override
	public Object visit(ConcatBinaryExpr e) {
		e.getLhs().accept(this);
		e.getRhs().accept(this);
		System.out.println("Concat expression: " + e.toString());
		return null;

	}

	@Override
	public Object visit(DivideBinaryExpr e) {
		e.getLhs().accept(this);
		e.getRhs().accept(this);
		System.out.println("Divide expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(DotBinaryExpr e) {
		System.out.println("Dot expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(EqBinaryExpr e) {
		e.getLhs().accept(this);
		e.getRhs().accept(this);
		System.out.println("Equal expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(ExprStmt e) {
		e.getE().accept(this);
		System.out.println("Divide expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(FcallExpr e) {
		String id = e.getId();
		SymbolEntry entry = symTab.get(id);
		/*if(entry == null){
			eh.printErrorMessage(id, "undeclared function", ErrorHandler.ErrorType.TYPE);
			return "error";
		}
		*/
		for (int i = 0; i < e.getParameters().size(); i++) {
			e.getParameters().get(i).accept(this);
		}
		System.out.println("Function call expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(FloatValueExpr e) {
		System.out.println("Float value expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(GreaterCompBinaryExpr e) {
		e.getLhs().accept(this);
		e.getRhs().accept(this);
		System.out.println("Greater than expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(GreaterOrEqExpr e) {
		e.getLhs().accept(this);
		e.getRhs().accept(this);
		System.out.println("Greater or eq expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(InExpr e) {
		e.getLhs().accept(this);
		e.getRhs().accept(this);
		System.out.println("In expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(IntValueExpr e) {
		System.out.println("Int value expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(LessCompBinaryExpr e) {
		e.getLhs().accept(this);
		e.getRhs().accept(this);
		System.out.println("Less than expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(LessEqCompBinaryExpr e) {
		e.getLhs().accept(this);
		e.getRhs().accept(this);
		System.out.println("Less or eq expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(MinusBinaryExpr e) {
		e.getLhs().accept(this);
		e.getRhs().accept(this);
		System.out.println("Minus expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(OrExpr e) {
		e.getLhs().accept(this);
		e.getRhs().accept(this);
		System.out.println("Or expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(PlusBinaryExpr e) {
		e.getLhs().accept(this);
		e.getRhs().accept(this);
		System.out.println("Plus expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(PowerBinaryExpr e) {
		e.getLhs().accept(this);
		e.getRhs().accept(this);
		System.out.println("Power expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(SeqCallExpr e) {
		e.getCall().accept(this);
		System.out.println("Seq call expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(SeqExpr e) {
		System.out.println("Seq expression: " + e.toString());
		if (e.getSequence() != null)
			for (int i = 0; i < e.getSequence().size(); i++) {
				e.getSequence().get(i).accept(this);
			}
		return null;
	}

	@Override
	public Object visit(SeqSlicingExpr e) {
		System.out.println("Seq slicing expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(StrValueExpr e) {
		System.out.println("Str value expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(TimesBinaryExpr e) {
		e.getLhs().accept(this);
		e.getRhs().accept(this);
		System.out.println("Times expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(ValueExpr e) {
		System.out.println("Value expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(VarExpr e) {
		SymbolEntry entry = symTab.get(e.getVar());
		if( entry == null ){
			eh.printErrorMessage(e.getVar(), "variable undefined",
					ErrorHandler.ErrorType.SCOPE_NOTDECL);
		}
		System.out.println("Var expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(Stmt s) {
		return null;

	}

	@Override
	public Object visit(ReturnStmt s) {
		s.getReturnExpr().accept(this);
		System.out.println("Return statement: " + s.toString());
		return null;
	}

	@Override
	public Object visit(IfStmt s) {
		s.getCondition().accept(this);
		System.out.println("If statement: " + s.toString());
		symTab = symTab.enterScope();
		if (s.getBody() != null)
			for (int i = 0; i < s.getBody().size(); i++) {
				s.getBody().get(i).accept(this);
			}
		symTab = symTab.exitScope();
		return null;
	}

	@Override
	public Object visit(RepeatUntilStmt s) {
		s.getCondition().accept(this);
		System.out.println("Repeat statement: " + s.toString());
		symTab = symTab.enterScope();
		if (s.getBody() != null)
			for (int i = 0; i < s.getBody().size(); i++) {
				s.getBody().get(i).accept(this);
			}
		symTab = symTab.exitScope();
		return null;
	}

	@Override
	public Object visit(IfElseStmt s) {
		s.getCondition().accept(this);
		System.out.println("If else statement: " + s.toString());
		symTab = symTab.enterScope();
		if (s.getIfBody() != null)
			for (int i = 0; i < s.getIfBody().size(); i++) {
				s.getIfBody().get(i).accept(this);
			}
		symTab = symTab.exitScope();
		symTab = symTab.enterScope();
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
		System.out.println("Var statement: " + s.toString());
		s.getVarDecl().accept(this);
		return null;
	}

	@Override
	public Object visit(WhileStmt s) {
		s.getCondition().accept(this);
		System.out.println("While statement: " + s.toString());
		symTab = symTab.enterScope();
		if (s.getBody() != null)
			for (int i = 0; i < s.getBody().size(); i++) {
				s.getBody().get(i).accept(this);
			}
		symTab = symTab.exitScope();
		return null;
	}

	public SymbolTable getSymTab() {
		return symTab;
	}

	@Override
	public String toString() {
		return "SymbolTableVisitor ["
				+ (symTab != null ? "symTab=" + symTab : "") + "]";
	}

	@Override
	public Object visit(NotUnaryExpr e) {
		e.getExpr().accept(this);
		return null;
	}

}
