package visitor;
import ast.*;

public interface Visitor {
	public Object visit(Program p);
	
	//declarations	
	public Object visit(Decl d);
	public Object visit(MainDecl d);
	public Object visit(VariableDecl d);
	public Object visit(FunctionDecl d);
	public Object visit(TypeDecl d);
	
	public Object visit(FunctionStmtList l);
	
	public Object visit(Field f);
	
	//expressions
	public Object visit(Expr e);
	public Object visit(AndExpr e);
	public Object visit(BoolValueExpr e);
	public Object visit(CharValueExpr e);
	public Object visit(CompBinaryExpr e);
	public Object visit(ConcatBinaryExpr e);
	public Object visit(DivideBinaryExpr e);
	public Object visit(DotBinaryExpr e);
	public Object visit(EqBinaryExpr e);
	public Object visit(ExprStmt e);
	public Object visit(FcallExpr e);	
	public Object visit(FloatValueExpr e);
	public Object visit(GreaterCompBinaryExpr e);
	public Object visit(GreaterOrEqExpr e);	
	public Object visit(InExpr e);
	public Object visit(IntValueExpr e);
	public Object visit(LessCompBinaryExpr e);
	public Object visit(LessEqCompBinaryExpr e);
	public Object visit(MinusBinaryExpr e);
	public Object visit(OrExpr e);
	public Object visit(PlusBinaryExpr e);
	public Object visit(PowerBinaryExpr e);
	public Object visit(SeqCallExpr e);
	public Object visit(SeqExpr e);
	public Object visit(SeqSlicingExpr e);
	public Object visit(StrValueExpr e);
	public Object visit(TimesBinaryExpr e);	
	public Object visit(ValueExpr e);
	public Object visit(VarExpr e);
	public Object visit(NotUnaryExpr e);
	
	//statements
	public Object visit(Stmt s);
	public Object visit(ReturnStmt s);
	public Object visit(IfStmt s);
	public Object visit(RepeatUntilStmt s);
	public Object visit(IfElseStmt s);
	public Object visit(VarStmt s);
	public Object visit(WhileStmt s);
}
