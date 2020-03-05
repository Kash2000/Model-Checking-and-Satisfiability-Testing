import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
	//Part 1
	//Reads .cnf file and converts it into clauses
    public static ArrayList<ArrayList<Integer>> clauseCreator(String fileName) {
        Scanner s = null;
        try {
            s = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> list2 = new ArrayList<>();
        
        while(s.hasNextLine()){
            String line = s.nextLine();

            if(line.charAt(0) == 'p'){
                while(s.hasNext()){
                    if(s.hasNextInt()) {
                        list.add(s.nextInt());
                    } else{
                        s.next();
                    }  
                }
                break;
            }
        }
        ArrayList<Integer> temp = new ArrayList<Integer>();
    
        for(int i = 0; i < list.size()-1; i++){
            if(list.get(i) != 0){
                temp.add(list.get(i));
            }
            else if(i == list.size() - 1){
                list2.add(temp);
            }
            else{
                list2.add(temp);
                temp = new ArrayList<Integer>();
            }
        }
        list2.add(temp);
        return list2;
    }

}