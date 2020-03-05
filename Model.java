import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
//Part 2
public class Model {

	public void chooseProblem(int number) {
		if(number == 1) {
			problem1();
		}else if(number == 2) {
			problem2();
		}else if(number == 3) {
			problem3();
		}
		else {
			System.out.println("This problem doesn't exist!");
		}
	}

	//Problem 1
	//{P,P->Q} |= Q.
	//(-P v Q) ^ P |= Q	
	public void problem1() {
		ArrayList<ArrayList<Integer>> kb = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> symbols = new ArrayList<Integer>();

		System.out.println("*Problem 1: {P,P->Q} |= Q*");
		int Q = 2;
		kb.add(new ArrayList<Integer>(Arrays.asList(1)));
		kb.add(new ArrayList<Integer>(Arrays.asList(-1, 2)));
		symbols.add(1);
		symbols.add(2);
		boolean q = TT_entails(new ArrayList<Integer>(Arrays.asList(Q)),symbols, kb);
		System.out.println("Result for TT_entails of Q: "+ q);

	}
	//Problem 2
	//Wumpus World Problem
	public void problem2() {
		ArrayList<ArrayList<Integer>> kb = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> symbols = new ArrayList<Integer>();

		System.out.println("*Problem 2: Wumpus World Problem*");
		//R1
		// 1 = P11
		kb.add(new ArrayList<Integer>(Arrays.asList(-1)));
		//R2
		//2 = B11
		//3 = P12
		//4 = P21
		kb.add(new ArrayList<Integer>(Arrays.asList(-2,3,4)));
		kb.add(new ArrayList<Integer>(Arrays.asList(2,-3)));
		kb.add(new ArrayList<Integer>(Arrays.asList(2,-4)));
		//R3
		//5 = B21
		//6 = P22
		//7 = P31
		kb.add(new ArrayList<Integer>(Arrays.asList(-5,1,6,7)));
		kb.add(new ArrayList<Integer>(Arrays.asList(5,-1)));
		kb.add(new ArrayList<Integer>(Arrays.asList(5,-6)));
		kb.add(new ArrayList<Integer>(Arrays.asList(5,-7)));
		//R7
		//8 = B12
		//9 = P13
		kb.add(new ArrayList<Integer>(Arrays.asList(-8,1,6,9)));
		kb.add(new ArrayList<Integer>(Arrays.asList(8,-1)));
		kb.add(new ArrayList<Integer>(Arrays.asList(8,-6)));
		kb.add(new ArrayList<Integer>(Arrays.asList(8,-9)));

		for(int i = 1;i<10;i++) {
			symbols.add(i);
		}

		//R4 - Adding first perception where the agent starts at [1,1]
		System.out.println("*Adding perception R4*");
		kb.add(new ArrayList<Integer>(Arrays.asList(-2)));

		//Testing for -3/not P12
		boolean not_3_p12 = TT_entails(new ArrayList<Integer>(Arrays.asList(-3)),symbols, kb);
		System.out.println("Result for TT_entails of not P12: "+not_3_p12);
		//Testing for -4/not P21
		boolean not_4_p21 = TT_entails(new ArrayList<Integer>(Arrays.asList(-4)),symbols, kb);
		System.out.println("Result for TT_entails of not P21: "+not_4_p21);
		//Testing for 6/P22
		boolean _6_p22 = TT_entails(new ArrayList<Integer>(Arrays.asList(6)),symbols, kb);
		System.out.println("Result for TT_entails of P22: "+_6_p22);
		//Testing for -6/not P22
		boolean not_6_p22 = TT_entails(new ArrayList<Integer>(Arrays.asList(-6)),symbols, kb);
		System.out.println("Result for TT_entails of not P22: "+not_6_p22);
		System.out.println("Therefore, the KB entails not P12 and not P21 but not (P22 or not P22). The agent doesn't knnow anything about P22.");

		//R5 - Adding perception where the agent moves to [2,1]
		System.out.println("\n*Adding perception R5*");
		kb.add(new ArrayList<Integer>(Arrays.asList(5)));

		//Testing for 6v7/P22vP31
		boolean or_6_7_p22vp31 = TT_entails(new ArrayList<Integer>(Arrays.asList(6,7)),symbols, kb);
		System.out.println("Result for TT_entails of P22vP31: "+or_6_7_p22vp31);
		//Testing for 6/P22
		_6_p22 = TT_entails(new ArrayList<Integer>(Arrays.asList(6)),symbols, kb);
		System.out.println("Result for TT_entails of P22: "+_6_p22);
		//Testing for -6/not P22
		not_6_p22 = TT_entails(new ArrayList<Integer>(Arrays.asList(-6)),symbols, kb);
		System.out.println("Result for TT_entails of not P22: "+not_6_p22);
		//Testing for 7/P31
		boolean _7_p31 = TT_entails(new ArrayList<Integer>(Arrays.asList(7)),symbols, kb);
		System.out.println("Result for TT_entails of P31: "+_7_p31);
		//Testing for 6v7/P22vP31
		boolean not_7_p31 = TT_entails(new ArrayList<Integer>(Arrays.asList(-7)),symbols, kb);
		System.out.println("Result for TT_entails of not P31: "+not_7_p31);
		System.out.println("Therefore, the KB entails P22vP31 but not P22, not (P22, P31 or not P31). The agent knows more but not enough.");

		//R6 - Adding perception where the agent moves to [1,2]
		System.out.println("\n*Adding perception R6*");
		kb.add(new ArrayList<Integer>(Arrays.asList(-8)));

		//Testing for 6v7/P22vP31
		not_6_p22 = TT_entails(new ArrayList<Integer>(Arrays.asList(-6)),symbols, kb);
		System.out.println("Result for TT_entails of not P22: "+not_6_p22);
		//Testing for 6/P22
		_7_p31 = TT_entails(new ArrayList<Integer>(Arrays.asList(7)),symbols, kb);
		System.out.println("Result for TT_entails of P31: "+_7_p31);
		System.out.println("Therefore, the KB entails both not P22 and P31 because it has enough knowledge.");
	}
	//Problem 3
	public void problem3() {
		System.out.println("*Problem 3: Mythical, Magical & Horned*");
		ArrayList<ArrayList<Integer>> kb = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> symbols = new ArrayList<Integer>();
		//Mythical =1
		//immortal=2 , mortal =-2
		//mammal =3
		//horned= 4
		//magical =5
		//1->2
		kb.add(new ArrayList<Integer>(Arrays.asList(-1,2))); 
		//-1->(-2^3)
		kb.add(new ArrayList<Integer>(Arrays.asList(1,-2))); 
		kb.add(new ArrayList<Integer>(Arrays.asList(1,3))); 
		//(2v3)-> 4
        kb.add(new ArrayList<Integer>(Arrays.asList(-2, 4)));
        kb.add(new ArrayList<Integer>(Arrays.asList(-3, 4)));
        //4-> 5
        kb.add(new ArrayList<Integer>(Arrays.asList(-4, 5)));
        for(int i = 1; i < 6; i++){
            symbols.add(i);
        }
        //Testing for Can we prove that a unicorn is mythical?
        boolean mythical = TT_entails(new ArrayList<Integer>(Arrays.asList(1)),symbols, kb);
		System.out.println("Result for 'Can we prove that a unicorn is mythical?': "+ mythical);
		System.out.println("We do not have enough information about mythical unicorns.");
		//Testing for Can we prove that a unicorn is magical?
		boolean magical = TT_entails(new ArrayList<Integer>(Arrays.asList(5)),symbols, kb);
		System.out.println("Result for 'Can we prove that a unicorn is magical?': "+ magical);
		//Testing for Can we prove that a unicorn is horned?
		boolean horned = TT_entails(new ArrayList<Integer>(Arrays.asList(4)),symbols, kb);
		System.out.println("Result for 'Can we prove that a unicorn is horned?': "+ horned);
	}

	//TT_entails goes through the truth table. If kb!=alpha in any part of the table:return false else return true
	//The model is stored in a Hashmap which represents the truth table
	boolean TT_entails(ArrayList<Integer> alpha, ArrayList<Integer> symbols, ArrayList<ArrayList<Integer>> kb){
		HashMap<Integer,Boolean> model = new HashMap<Integer,Boolean>();
		return TT_checkAll(kb, alpha, symbols, model);
	}

	//Checks all the truth table rows
	boolean TT_checkAll(ArrayList<ArrayList<Integer>> kb, ArrayList<Integer> alpha, ArrayList<Integer> symbols, HashMap<Integer,Boolean> model){
		//System.out.println(model);
		if(symbols.isEmpty()){
			boolean pl = PL_true_kb(kb,model);
			if(pl){
				return PL_true(alpha, model);
			}else{
				return true; //when kb is false, always return true
			}
		}
		else{ 
			Integer P = symbols.get(0);
			ArrayList<Integer> rest = new ArrayList<Integer>(symbols.subList(1, symbols.size()));
			return (TT_checkAll(kb, alpha, rest, union(model, P, Boolean.TRUE)) && TT_checkAll(kb, alpha, rest, union(model, P, Boolean.FALSE))) ;    
		}
	}

	public Boolean PL_true(ArrayList<Integer> alpha, HashMap<Integer,Boolean> model){
		return clause_eval(alpha, model);
	}

	public Boolean PL_true_kb(ArrayList<ArrayList<Integer>> sentence,HashMap<Integer,Boolean> model){
		Boolean result = Boolean.TRUE;
		for(int i = 0; i < sentence.size(); i++){
			ArrayList<Integer> clause = sentence.get(i);
			result = result && clause_eval(clause,model);
		}
		return result;
	}
	private Boolean clause_eval(ArrayList<Integer> clause, HashMap<Integer, Boolean> model){
		Boolean result = Boolean.FALSE;
		for(int i = 0; i < clause.size(); i++){
			int x = clause.get(i);
			Boolean b = model.get(Math.abs(x));
			if(b != null){
				boolean bo = integer_eval(clause.get(i), model);
				result = result || bo; 
			}
		}
		return result;
	}

	private Boolean integer_eval(Integer atom, HashMap<Integer, Boolean> model){
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

	private HashMap<Integer,Boolean> union(HashMap<Integer,Boolean> model, Integer key, Boolean b){
		HashMap<Integer,Boolean> toReturn = new HashMap<Integer,Boolean>();
		model.put(key,b);

		toReturn.putAll(model);
		return toReturn;
	}
}
