import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

//Part 3
public class Satisfiable {
	private ArrayList<Integer> heuristic_values = new ArrayList<>();

	public boolean GSATAlgorithm(ArrayList<ArrayList<Integer>> clauses, int max_tries, int max_flips, int variable_count, boolean trace) {
		HashMap<Integer,Boolean> T = new HashMap<Integer,Boolean>();
		for(int i = 1;i<max_tries;i++) {
			randomlyGenerateTruthAssignments(T, variable_count);
			for(int j = 0; j<max_flips;j++) {
				heuristic_values.clear();
				if(GSAT_helper(clauses, T)) {
					System.out.printf("After trying "+i+" time(s) and conducting "+max_flips+" flips each time,\n");
					System.out.println("This proposition is satisfiable and here is the row in the truth table that results in true: ");
					System.out.println(T.values().toString());
					return true;
				}
				for(int l = 1;l<=variable_count;l++) {
					try_flip(clauses, T, l);
				}
				T = perform_flip(T, trace);
				if(trace) {
					System.out.println(T.values().toString()+"\n");
				}
			}
		}
		return false;
	}
	public void randomlyGenerateTruthAssignments(HashMap<Integer, Boolean> T, int variable_count) {
		for(int i = 1;i<=variable_count;i++) {
			if(Math.random() > 0.5) {
				T.put(i, Boolean.TRUE);
			}else {
				T.put(i, Boolean.FALSE);
			}
		}
	}
	public Boolean GSAT_helper(ArrayList<ArrayList<Integer>> sentence, HashMap<Integer,Boolean> model) {
		Model m = new Model();
		return m.PL_true_kb(sentence, model);
	}

	public HashMap<Integer,Boolean> perform_flip(HashMap<Integer, Boolean> T, boolean trace) {
		int max_heuristic = Collections.max(heuristic_values);
		int index = heuristic_values.indexOf(max_heuristic);
		if(T.get(index+1) == Boolean.FALSE) {
			T.replace(index+1, Boolean.TRUE);
		}else {
			T.replace(index+1, Boolean.FALSE);
		}
		if(trace) {
			System.out.println("Value at index "+index+" changed due to highest heuristic value.");
		}
		return T;
	}

	public Boolean try_flip(ArrayList<ArrayList<Integer>> sentence, HashMap<Integer, Boolean> model, int index){
		int heuristic = 0;
		Boolean result = Boolean.TRUE;
		for(int i = 0; i < sentence.size(); i++){
			ArrayList<Integer> clause = sentence.get(i);
			Boolean clauseEval = clause_eval(clause,model, index);
			//System.out.println("h" + clauseEval);
			result = result && clauseEval;
			if(clauseEval == Boolean.TRUE) {
				heuristic++;
				//System.out.println(heuristic);
			}
		}
		heuristic_GSAT(heuristic);
		return result;
	}
	private Boolean clause_eval(ArrayList<Integer> clause, HashMap<Integer, Boolean> model, int index){
		Boolean result = Boolean.FALSE;
		ArrayList<Integer> clause2 = new ArrayList<>();
		for(int i = 0; i < clause.size(); i++){
			clause2 = (ArrayList<Integer>) clause.clone();
			int x = clause.get(i);
			Boolean b = model.get(Math.abs(x));
			if(b != null){
				if(Math.abs(x) == index) {
					clause2.set(i, x*-1);
				}
				boolean bo = integer_eval(clause2.get(i), model, index);
				result = result || bo; 
				//System.out.println("bo:" + bo);
				if(Math.abs(x) == index) {
					clause2.set(i, x*-1);
				}
			}
		}
		return result;
	}

	private Boolean integer_eval(Integer atom, HashMap<Integer, Boolean> model, int index){
		Boolean result = null; 
		if(atom < 0){
			Boolean assignment = model.get(Math.abs(atom));
			if(assignment != null){
				if(assignment.booleanValue() == true){
					return Boolean.FALSE;
				}else{ 
					return Boolean.TRUE;
				}
			}
		}
		return model.get(atom);
	}

	public void heuristic_GSAT(int heuristic) {
		heuristic_values.add(heuristic);
	}
	//Number of holes(m) = Number of pigeons(n)
    public ArrayList<ArrayList<Integer>> getKB_PigeonHole(int m) {
        ArrayList<ArrayList<Integer>> kb = new ArrayList<ArrayList<Integer>>();
        int n = m; 
        if (m == 1) {
            kb.add(new ArrayList<Integer>(Arrays.asList(1)));
			return kb;
        } else {
            for (int p = 1; p <= n; p++) { //adding normal clauses
                ArrayList<Integer> conjunctions = new ArrayList<Integer>();
                for (int h = 1; h <= m; h++) {
                    int i = (p-1)*m + h; 
                    conjunctions.add(i);
                }
                kb.add(conjunctions);
            }

            for (int i = 0; i < n; i++) { //adding constraints
                for (int j = 0; j < m-1; j++) {
                    for (int k = j+1; k < m; k++) {
                        ArrayList<Integer> conjunctions = new ArrayList<Integer>();
                        conjunctions.add(-kb.get(j).get(i));
                        conjunctions.add(-kb.get(k).get(i));
                        kb.add(conjunctions);
                    }
                }
            }

        }

        return kb;
    }
}
