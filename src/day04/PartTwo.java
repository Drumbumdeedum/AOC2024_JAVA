package day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PartTwo {
    public static void main(String[] args) {
        String fileName = "src/day04/input.txt";
        
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            ArrayList<ArrayList<String>> stringMatrix = new ArrayList<>();
            while((line = reader.readLine()) != null) {
                ArrayList<String> subList = new ArrayList<>(Arrays.asList(line.split("")));
                stringMatrix.add(subList);
            }
            int total = 0;
            for (int i = 1; i < stringMatrix.size() - 1; i++) {
                for (int j = 1; j < stringMatrix.get(i).size() -1; j++) {
                    String character = stringMatrix.get(i).get(j);
                    if(character.equals("A")) {
                        String axisOne = "";
                        String axisTwo = "";
                        axisOne = axisOne.concat(stringMatrix.get(i - 1).get(j + 1));
                        axisTwo = axisTwo.concat(stringMatrix.get(i + 1).get(j + 1));
                        axisOne = axisOne.concat(stringMatrix.get(i + 1).get(j - 1));
                        axisTwo = axisTwo.concat(stringMatrix.get(i - 1).get(j - 1));
                        
                        if((axisOne.equals("MS") || axisOne.equals("SM")) && (axisTwo.equals("MS") || axisTwo.equals("SM"))) {
                            total++;
                        }
                    }
                }
            }
            System.out.println(total);
            
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
