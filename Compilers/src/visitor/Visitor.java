package visitor;
import ast.*;

public interface Visitor {
	public void visit(Program p);
	
	//declarations	
	public void visit(Decl d);
	public void visit(MainDecl d);
	public void visit(VariableDecl d);
	public void visit(FunctionDecl d);
	public void visit(TypeDecl d);
	
	public void visit(FunctionStmtList l);
	
	public void visit(Field f);
	
	//expressions
	public void visit(Expr e);
	public void visit(AndExpr e);
	public void visit(BoolValueExpr e);
	public void visit(CharValueExpr e);
	public void visit(CompBinaryExpr e);
	public void visit(ConcatBinaryExpr e);
	public void visit(DivideBinaryExpr e);
	public void visit(DotBinaryExpr e);
	public void visit(EqBinaryExpr e);
	public void visit(ExprStmt e);
	public void visit(FcallExpr e);	
	public void visit(FloatValueExpr e);
	public void visit(GreaterCompBinaryExpr e);
	public void visit(GreaterOrEqExpr e);	
	public void visit(InExpr e);
	public void visit(IntValueExpr e);
	public void visit(LessCompBinaryExpr e);
	public void visit(LessEqCompBinaryExpr e);
	public void visit(MinusBinaryExpr e);
	public void visit(OrExpr e);
	public void visit(PlusBinaryExpr e);
	public void visit(PowerBinaryExpr e);
	public void visit(SeqCallExpr e);
	public void visit(SeqExpr e);
	public void visit(SeqSlicingExpr e);
	public void visit(StrValueExpr e);
	public void visit(TimesBinaryExpr e);	
	public void visit(ValueExpr e);
	public void visit(VarExpr e);
	
	//statements
	public void visit(Stmt s);
	public void visit(ReturnStmt s);
	public void visit(IfStmt s);
	public void visit(RepeatUntilStmt s);
	public void visit(IfElseStmt s);
	public void visit(VarStmt s);
	public void visit(WhileStmt s);
}
