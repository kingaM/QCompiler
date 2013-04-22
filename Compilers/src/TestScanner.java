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
		System.setIn(new FileInputStream("t4f"));

		Yylex myScanner = new Yylex(System.in);
		QCup myParser = new QCup(myScanner);

		Symbol result = myParser.parse();
		Program p = (Program) result.value;
		if (p != null) {
			
			ErrorHandler eh = new ErrorHandler(QCup.numOfErrors);
			SymbolTable st = new SymbolTable(eh); 
			
			p.accept(new SymbolTableVisitor(st, eh));
			p.accept(new TypeScopeVisitor(st, eh));
			boolean success = eh.printSummary();
			if(success) {
				System.out.println("\nCode generated via the AST:");
				System.out.println(p.toString());
			}
		}
	}
}