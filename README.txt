README 
PROJECT 2 CSC 242
Spring 2020
KASHISH DEWNANI (kdewnani)

Collaborator: SHIVALI SINGH (ssingh53)


How to run: Use Eclipse IDE and run with default project settings. 
You will be prompted to type your file path in for nqueens4.cnf, quinn.cnf & hole6.cnf and to choose the number of pigeons and holes for the PHP. Rest is self-explanatory.

Reader.java : Reads the cnf file  (Part 1)

Model.java : Contains TT-entails model checking algorithm 
Here we convert the sentences to conjunctive normal form. Then create a knowledge base of clauses to test. (Part 2)

Tests for 
1.	P, P ->Q |= Q
2.	Wumpus World example
3.	Test if Unicorn is: Mythical, Magical, Horned?

Satisfiable.java: Implement GSAT algorithm & Pigeonhole function to create clauses. (Part 3)

Tests for:
1.	(x1 ∨ x3 ∨ ¬x4) ∧ (x4) ∧ (x2 ∨ ¬x3) 
2.	N Queens for N=4
3.	Quinn.cnf 
4.	Hole6.cnf
5.	Pigenhole Problem: We have created a function that creates clauses for equal number of holes and pigeons.

Main.java: Tests all parts



