import java.io.*;

public class TestScanner {

	public static void main(String args[]) throws Exception {

		System.setIn(new FileInputStream("testfile.txt"));
		//System.setIn(new FileInputStream("test03"));
		Yylex myScanner = new Yylex(System.in);
		QCup myParser = new QCup(myScanner);

		myParser.parse();
	}
}