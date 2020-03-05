import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[]args) {
		Scanner scan = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		//Part 2
		System.out.println("Part 2\n");
		Model model = new Model();
		model.chooseProblem(1);
		System.out.println("");
		model.chooseProblem(2);
		System.out.println("");
		model.chooseProblem(3);
		System.out.println("");

		//Part 1 and 3
		System.out.println("Part 1(File reader) and Part 3: ");
		Satisfiable s = new Satisfiable();

		//Problem 1
		ArrayList<ArrayList<Integer>> clauses = new ArrayList<ArrayList<Integer>>();
		clauses.clear();
		clauses.add(new ArrayList<Integer>(Arrays.asList(1,3,-4)));
		clauses.add(new ArrayList<Integer>(Arrays.asList(4)));
		clauses.add(new ArrayList<Integer>(Arrays.asList(2,-3)));
		System.out.println("Testing for Problem 1...");
		System.out.println("Tracing Enabled");
		boolean a = s.GSATAlgorithm(clauses,10,10,4, true);
		System.out.println("Answer for Problem 1: "+a+"\n");

		//nqueens_4
		clauses.clear();
		Reader r = new Reader();
		//C:\\Users\\kashi\\Desktop\\nqueens_4.cnf
		System.out.println("Please enter your file path here for nqueens4.cnf: ");
		String f = scan2.nextLine();
		System.out.println("Reading file...");
		clauses = r.clauseCreator(f);
		System.out.println("Testing for nqueens_4.cnf...");
		System.out.println("Tracing Disabled");
		a = s.GSATAlgorithm(clauses,50,100,16, false);
		System.out.println("Answer for nqueens_4.cnf: "+a+"\n");

		//quinn.cnf
		clauses.clear();
		//C:\\Users\\kashi\\Desktop\\quinn.cnf
		System.out.println("Please enter your file path here for quinn.cnf: ");
		f = scan2.nextLine();
		System.out.println("Reading file...");
		clauses = r.clauseCreator(f);
		System.out.println("Testing for quinn.cnf...");
		System.out.println("Tracing Disabled");
		a = s.GSATAlgorithm(clauses,50,100,16, false);
		System.out.println("Answer for quinn.cnf: "+a+"\n");

		//Pigeon Hole Problem n pigeons m holes where n = m
		clauses.clear();
		System.out.println("PHP:\nChoose how many pigeons and holes you would like (Note: They are both equal): ");
		int m = scan.nextInt();
		clauses = s.getKB_PigeonHole(m);
		System.out.println("Testing for Pigeon Hole Problem with "+m+" pigeons "+ m+" holes...");
		if(m>5) {
			System.out.println("Tracing Disabled");
			a = s.GSATAlgorithm(clauses,500,1000,m*m, false);
		}else {
			System.out.println("Tracing Enabled");
			a = s.GSATAlgorithm(clauses,50,100,m*m, true);
		}
		System.out.println("\nAnswer for Problem Pigeon Hole Problem with "+m+" pigeons "+ m+" holes: "+a+"");
		System.out.println("If n > m then this would be unsatisfiable(as shown below) and if n < m then it would be easier to solve for satisfiability.\n");

		//hole6.cnf to show unsatisfiability
		clauses.clear();
		//C:\\Users\\kashi\\Desktop\\hole6.cnf
		System.out.println("Please enter your file path here for hole6.cnf: ");
		f = scan2.nextLine();
		System.out.println("Reading file...");
		clauses = r.clauseCreator(f);
		System.out.println("Testing for hole6.cnf...");
		System.out.println("Tracing Disabled");
		a = s.GSATAlgorithm(clauses,50,100,42, false);
		System.out.println("Answer for hole6.cnf: "+a+"\n");
		System.out.println("Unlike with the Pigeon Hole Problem shown above with equal pigeons and holes, this problem has more "
				+ "pigeons than holes and is therefore unsatisfiable.");

	}
}
