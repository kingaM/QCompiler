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
	public void visit(Program p) {
		System.out.println("Entering program");
		
		symTab = symTab.enterScope(); 
		for ( int i = 0; i < p.getDecllist().size(); i++ ) {
	        p.getDecllist().get(i).accept(this);
	    }		
		p.getMain().accept(this);
		symTab = symTab.exitScope(); 
	}
	
	@Override
	public void visit(Decl d) {	
	}
	
	@Override
	public void visit(MainDecl d) {
		System.out.println("Entering main decl");		
		symTab = symTab.enterScope(); 
		for ( int i = 0; i < d.getStmts().size(); i++ ) {
	        d.getStmts().get(i).accept(this);
	    }	
		symTab = symTab.exitScope(); 
	}
	
	@Override
	public void visit(VariableDecl d) {
		//has id type
		System.out.println("Entering variable decl: " + d.toString());
		String id = d.getId();
		String type = d.getType();
		symTab.put(id, SymbolType.TDEF, type);
		for ( int i = 0; i < d.getInit().size(); i++ ) {
	        d.getInit().get(i).accept(this);
	    }	
	}

	@Override
	public void visit(FunctionDecl d) {
		System.out.println("Entering function decl: " + d.toString());
		String id = d.getId();
		String signature = d.getReturnType() + "->" + getSignature(d.getFieldDecl());
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
	public void visit(TypeDecl d) {
		System.out.println("Entering type decl: " + d.toString());	
		String id = d.getId();
		String signature = getSignature(d.getFields());
		symTab.put(id, SymbolType.TDEF, signature);
		for ( int i = 0; i < d.getFields().size(); i++ ) {
			d.getFields().get(i).accept(this);
		}		
	}

	@Override
	public void visit(FunctionStmtList l) {
		for ( int i = 0; i < l.getStmtList().size(); i++ ) {
			l.getStmtList().get(i).accept(this);
		}	

	}

	@Override
	public void visit(Field f) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void visit(Expr e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(AndExpr e) {
		System.out.println("Add expression: " + e.toString());
		
	}

	@Override
	public void visit(BoolValueExpr e) {
		System.out.println("Bool expression: " + e.toString());
		
	}

	@Override
	public void visit(CharValueExpr e) {
		System.out.println("Char Value expression: " + e.toString());
		
	}

	@Override
	public void visit(CompBinaryExpr e) {
		System.out.println("Comp expression: " + e.toString());
		
	}

	@Override
	public void visit(ConcatBinaryExpr e) {
		System.out.println("Concat expression: " + e.toString());
		
	}

	@Override
	public void visit(DivideBinaryExpr e) {
		System.out.println("Divide expression: " + e.toString());		
	}

	@Override
	public void visit(DotBinaryExpr e) {
		System.out.println("Dot expression: " + e.toString());		
	}

	@Override
	public void visit(EqBinaryExpr e) {
		System.out.println("Equal expression: " + e.toString());		
	}

	@Override
	public void visit(ExprStmt e) {
		System.out.println("Divide expression: " + e.toString());		
	}

	@Override
	public void visit(FcallExpr e) {
		System.out.println("Function call expression: " + e.toString());		
	}

	@Override
	public void visit(FloatValueExpr e) {
		System.out.println("Float value expression: " + e.toString());		
	}

	@Override
	public void visit(GreaterCompBinaryExpr e) {
		System.out.println("Greater than expression: " + e.toString());		
	}

	@Override
	public void visit(GreaterOrEqExpr e) {
		System.out.println("Greater or eq expression: " + e.toString());		
	}

	@Override
	public void visit(InExpr e) {
		System.out.println("In expression: " + e.toString());		
	}

	@Override
	public void visit(IntValueExpr e) {
		System.out.println("Int value expression: " + e.toString());		
	}

	@Override
	public void visit(LessCompBinaryExpr e) {
		System.out.println("Less than expression: " + e.toString());		
	}

	@Override
	public void visit(LessEqCompBinaryExpr e) {
		System.out.println("Less or eq expression: " + e.toString());		
	}

	@Override
	public void visit(MinusBinaryExpr e) {
		System.out.println("Minus expression: " + e.toString());		
	}

	@Override
	public void visit(OrExpr e) {
		System.out.println("Or expression: " + e.toString());		
	}

	@Override
	public void visit(PlusBinaryExpr e) {
		System.out.println("Plus expression: " + e.toString());		
	}

	@Override
	public void visit(PowerBinaryExpr e) {
		System.out.println("Power expression: " + e.toString());		
	}

	@Override
	public void visit(SeqCallExpr e) {
		System.out.println("Seq call expression: " + e.toString());		
	}

	@Override
	public void visit(SeqExpr e) {
		System.out.println("Seq expression: " + e.toString());	
		for ( int i = 0; i < e.getSequence().size(); i++ ) {
	        e.getSequence().get(i).accept(this);
	    }
	}

	@Override
	public void visit(SeqSlicingExpr e) {
		System.out.println("Seq slicing expression: " + e.toString());		
	}

	@Override
	public void visit(StrValueExpr e) {
		System.out.println("Str value expression: " + e.toString());		
	}

	@Override
	public void visit(TimesBinaryExpr e) {
		System.out.println("Times expression: " + e.toString());		
	}

	@Override
	public void visit(ValueExpr e) {
		System.out.println("Value expression: " + e.toString());		
	}

	@Override
	public void visit(VarExpr e) {
		System.out.println("Var expression: " + e.toString());		
	}
	
	@Override
	public void visit(Stmt s) {
		System.out.println("Entering statement");
		
	}

	@Override
	public void visit(ReturnStmt s) {
		System.out.println("Return statement: " + s.toString());		
	}

	@Override
	public void visit(IfStmt s) {
		System.out.println("If statement: " + s.toString());		
		symTab.enterScope();
		for ( int i = 0; i < s.getBody().size(); i++ ) {
			s.getBody().get(i).accept(this);
		}	
		symTab.exitScope();
	}

	@Override
	public void visit(RepeatUntilStmt s) {
		System.out.println("Repeat statement: " + s.toString());	
		symTab.enterScope();
		for ( int i = 0; i < s.getBody().size(); i++ ) {
			s.getBody().get(i).accept(this);
		}	
		symTab.exitScope();
		
	}

	@Override
	public void visit(IfElseStmt s) {
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
		
	}

	@Override
	public void visit(VarStmt s) {
		System.out.println("Var statement: " + s.toString());		
		s.getVarDecl().accept(this);
	}

	@Override
	public void visit(WhileStmt s) {
		System.out.println("While statement: " + s.toString());		
		symTab.enterScope();
		for ( int i = 0; i < s.getBody().size(); i++ ) {
			s.getBody().get(i).accept(this);
		}	
		symTab.exitScope();
		
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
