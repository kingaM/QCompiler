import java.io.FileInputStream;

import java_cup.runtime.Symbol;
import visitor.SymbolTable;
import visitor.SymbolTableVisitor;
import visitor.TypeScopeVisitor;
import ast.Program;
import visitor.ErrorHandler;

public class TestScanner {

	public static void main(String args[]) throws Exception {

		System.setIn(new FileInputStream("testfile.txt"));
		 //System.setIn(new FileInputStream("test05"));

		Yylex myScanner = new Yylex(System.in);
		QCup myParser = new QCup(myScanner);

		Symbol result = myParser.parse();
		Program p = (Program) result.value;
		if (p != null) {
			System.out.println("My result: " + p.toString());
			ErrorHandler eh = new ErrorHandler(QCup.numOfErrors);
			SymbolTable st = new SymbolTable(eh); 
			
			p.accept(new SymbolTableVisitor(st, eh));
			p.accept(new TypeScopeVisitor(st, eh));
			eh.printSummary();
		}
	}
}