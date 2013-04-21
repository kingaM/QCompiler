import java.io.FileInputStream;

import java_cup.runtime.Symbol;
import visitor.SymbolTable;
import visitor.SymbolTableVisitor;
import visitor.TypeScopeVisitor;
import ast.Program;

public class TestScanner {

	public static void main(String args[]) throws Exception {

		System.setIn(new FileInputStream("testfile.txt"));
		// System.setIn(new FileInputStream("test05"));

		Yylex myScanner = new Yylex(System.in);
		QCup myParser = new QCup(myScanner);

		Symbol result = myParser.parse();
		Program p = (Program) result.value;
		if (p != null) {
			System.out.println("My result: " + p.toString());
			SymbolTable st = new SymbolTable();
			p.accept(new SymbolTableVisitor(st));
			p.accept(new TypeScopeVisitor(st));
			System.out.println("\nFinal " + st.toString());
		}
	}
}