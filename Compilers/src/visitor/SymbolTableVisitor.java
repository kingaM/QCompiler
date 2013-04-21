package visitor;

import java.util.ArrayList;

import ast.*;


public class SymbolTableVisitor implements Visitor {
	
	private SymbolTable symTab; 
	
	public SymbolTableVisitor(SymbolTable st)
	{
		symTab = st; 		
	}
	
	@Override
	public Object visit(Program p) {
		System.out.println("Entering program");
		
		symTab = symTab.enterScope(); 
		for ( int i = 0; i < p.getDecllist().size(); i++ ) {
	        p.getDecllist().get(i).accept(this);
	    }		
		p.getMain().accept(this);
		symTab = symTab.exitScope(); 
		return null;
	}
	
	@Override
	public Object visit(Decl d) {
		return null;
	}
	
	@Override
	public Object visit(MainDecl d) {
		System.out.println("Entering main decl");		
		symTab = symTab.enterScope(); 
		for ( int i = 0; i < d.getStmts().size(); i++ ) {
	        d.getStmts().get(i).accept(this);
	    }	
		symTab = symTab.exitScope(); 
		return null;
	}
	
	@Override
	public Object visit(VariableDecl d) {
		//has id type
		System.out.println("Entering variable decl: " + d.toString());
		String id = d.getId();
		String type = d.getType();
		symTab.put(id, SymbolType.TDEF, type);
		for ( int i = 0; i < d.getInit().size(); i++ ) {
	        d.getInit().get(i).accept(this);
	    }	
		return null;
	}

	@Override
	public Object visit(FunctionDecl d) {
		System.out.println("Entering function decl: " + d.toString());
		String id = d.getId();
		String signature = getSignature(d.getFieldDecl());
		symTab.put(id, SymbolType.FDEF, signature, d.getReturnType());
		symTab = symTab.enterScope(); 
		if(d.getFieldDecl() != null){
			for ( int i = 0; i < d.getFieldDecl().size(); i++ ) {
				d.getFieldDecl().get(i).accept(this);
			}
		}
		for ( int i = 0; i < d.getBody().size(); i++ ) {
			d.getBody().get(i).accept(this);
		}		
		symTab = symTab.exitScope(); 
		return null;
	}

	private String getSignature(ArrayList<Field> fieldDecl) {
		
		if(fieldDecl != null){
			String sig = "";
			for ( int i = 0; i < fieldDecl.size(); i++ ) {
				sig = sig + "x" + fieldDecl.get(i).getType();
			}
			return sig;
		}
		
		return "void";
	}

	@Override
	public Object visit(TypeDecl d) {
		System.out.println("Entering type decl: " + d.toString());	
		String id = d.getId();
		String signature = getSignature(d.getFields());
		symTab.put(id, SymbolType.TDEF, signature);
		for ( int i = 0; i < d.getFields().size(); i++ ) {
			d.getFields().get(i).accept(this);
		}		
		return null;
	}

	@Override
	public Object visit(FunctionStmtList l) {
		for ( int i = 0; i < l.getStmtList().size(); i++ ) {
			l.getStmtList().get(i).accept(this);
		}	
		return null;
	}

	@Override
	public Object visit(Field f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Object visit(Expr e) {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public Object visit(AndExpr e) {
		System.out.println("Add expression: " + e.toString());
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
		System.out.println("Comp expression: " + e.toString());
		return null;
		
	}

	@Override
	public Object visit(ConcatBinaryExpr e) {
		System.out.println("Concat expression: " + e.toString());
		return null;
		
	}

	@Override
	public Object visit(DivideBinaryExpr e) {
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
		System.out.println("Equal expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(ExprStmt e) {
		System.out.println("Divide expression: " + e.toString());	
		return null;
	}

	@Override
	public Object visit(FcallExpr e) {
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
		System.out.println("Greater than expression: " + e.toString());	
		return null;
	}

	@Override
	public Object visit(GreaterOrEqExpr e) {
		System.out.println("Greater or eq expression: " + e.toString());
		return null;
	}

	@Override
	public Object visit(InExpr e) {
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
		System.out.println("Less than expression: " + e.toString());	
		return null;
	}

	@Override
	public Object visit(LessEqCompBinaryExpr e) {
		System.out.println("Less or eq expression: " + e.toString());	
		return null;
	}

	@Override
	public Object visit(MinusBinaryExpr e) {
		System.out.println("Minus expression: " + e.toString());	
		return null;
	}

	@Override
	public Object visit(OrExpr e) {
		System.out.println("Or expression: " + e.toString());	
		return null;
	}

	@Override
	public Object visit(PlusBinaryExpr e) {
		System.out.println("Plus expression: " + e.toString());		
		return null;
	}

	@Override
	public Object visit(PowerBinaryExpr e) {
		System.out.println("Power expression: " + e.toString());	
		return null;
	}

	@Override
	public Object visit(SeqCallExpr e) {
		System.out.println("Seq call expression: " + e.toString());	
		return null;
	}

	@Override
	public Object visit(SeqExpr e) {
		System.out.println("Seq expression: " + e.toString());	
		for ( int i = 0; i < e.getSequence().size(); i++ ) {
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
		System.out.println("Var expression: " + e.toString());
		return null;
	}
	
	@Override
	public Object visit(Stmt s) {
		System.out.println("Entering statement");
		return null;
		
	}

	@Override
	public Object visit(ReturnStmt s) {
		System.out.println("Return statement: " + s.toString());	
		return null;
	}

	@Override
	public Object visit(IfStmt s) {
		System.out.println("If statement: " + s.toString());		
		symTab.enterScope();
		for ( int i = 0; i < s.getBody().size(); i++ ) {
			s.getBody().get(i).accept(this);
		}	
		symTab.exitScope();
		return null;
	}

	@Override
	public Object visit(RepeatUntilStmt s) {
		System.out.println("Repeat statement: " + s.toString());	
		symTab.enterScope();
		for ( int i = 0; i < s.getBody().size(); i++ ) {
			s.getBody().get(i).accept(this);
		}	
		symTab.exitScope();
		return null;
	}

	@Override
	public Object visit(IfElseStmt s) {
		System.out.println("If else statement: " + s.toString());	
		symTab.enterScope();
		for ( int i = 0; i < s.getIfBody().size(); i++ ) {
			s.getIfBody().get(i).accept(this);
		}	
		symTab.exitScope();
		symTab.enterScope();
		for ( int i = 0; i < s.getElseBody().size(); i++ ) {
			s.getElseBody().get(i).accept(this);
		}	
		symTab.exitScope();
		return null;
	}

	@Override
	public Object visit(VarStmt s) {
		System.out.println("Var statement: " + s.toString());		
		s.getVarDecl().accept(this);
		return null;
	}

	@Override
	public Object visit(WhileStmt s) {
		System.out.println("While statement: " + s.toString());		
		symTab.enterScope();
		for ( int i = 0; i < s.getBody().size(); i++ ) {
			s.getBody().get(i).accept(this);
		}	
		symTab.exitScope();
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

}