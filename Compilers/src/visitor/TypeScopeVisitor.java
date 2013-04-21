package visitor;

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

public class TypeScopeVisitor  implements Visitor {
	
	SymbolTable symTab;

	public TypeScopeVisitor(SymbolTable st) {
		symTab = st;
	}

	@Override
	public Object visit(Program p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Decl d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(MainDecl d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(VariableDecl d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(FunctionDecl d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(TypeDecl d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(FunctionStmtList l) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(BoolValueExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(CharValueExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(CompBinaryExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ConcatBinaryExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(DivideBinaryExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(DotBinaryExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(EqBinaryExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ExprStmt e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(FcallExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(FloatValueExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(GreaterCompBinaryExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(GreaterOrEqExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(InExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(IntValueExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(LessCompBinaryExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(LessEqCompBinaryExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(MinusBinaryExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(OrExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(PlusBinaryExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(PowerBinaryExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(SeqCallExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(SeqExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(SeqSlicingExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(StrValueExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(TimesBinaryExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ValueExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(VarExpr e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Stmt s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ReturnStmt s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(IfStmt s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(RepeatUntilStmt s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(IfElseStmt s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(VarStmt s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(WhileStmt s) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
