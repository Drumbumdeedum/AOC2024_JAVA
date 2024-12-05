package day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
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
            for (int i = 0; i < stringMatrix.size(); i++) {
                for (int j = 0; j < stringMatrix.get(i).size(); j++) {
                    String character = stringMatrix.get(i).get(j);
                    if(character.equals("X")) {
                        // CHECK IF MOVE UP AND RIGHT IS POSSIBLE
                        if(i - 3 >= 0 && j + 3 < stringMatrix.get(i).size()) {
                            String result = "X";
                            for (int k = 1; k < 4; k++) {
                                result = result.concat(stringMatrix.get(i - k).get(j + k));
                            }
                            if(result.equals("XMAS")) {
                                total ++;
                            }
                        }
                        // CHECK IF MOVE DOWN AND RIGHT IS POSSIBLE
                        if(i + 3 < stringMatrix.size() && j + 3 < stringMatrix.get(i).size()) {
                            String result = "X";
                            for (int k = 1; k < 4; k++) {
                                result = result.concat(stringMatrix.get(i + k).get(j + k));
                            }
                            if(result.equals("XMAS")) {
                                total ++;
                            }
                        }
                        // CHECK IF MOVE DOWN AND LEFT IS POSSIBLE
                        if(i + 3 < stringMatrix.size() && j - 3 >= 0) {
                            String result = "X";
                            for (int k = 1; k < 4; k++) {
                                result = result.concat(stringMatrix.get(i + k).get(j - k));
                            }
                            if(result.equals("XMAS")) {
                                total ++;
                            }
                        }
                        // CHECK IF MOVE UP AND LEFT IS POSSIBLE
                        if(i - 3 >= 0 && j - 3 >= 0) {
                            String result = "X";
                            for (int k = 1; k < 4; k++) {
                                result = result.concat(stringMatrix.get(i - k).get(j - k));
                            }
                            if(result.equals("XMAS")) {
                                total ++;
                            }
                        }
                        // CHECK IF MOVE UP IS POSSIBLE
                        if(i - 3 >= 0) {
                            String result = "X";
                            for (int k = 1; k < 4; k++) {
                                result = result.concat(stringMatrix.get(i - k).get(j));
                            }
                            if(result.equals("XMAS")) {
                                total ++;
                            }
                        }
                        // CHECK IF MOVE RIGHT IS POSSIBLE
                        if(j + 3 < stringMatrix.get(i).size()) {
                            String result = "X";
                            for (int k = 1; k < 4; k++) {
                                result = result.concat(stringMatrix.get(i).get(j + k));
                            }
                            if(result.equals("XMAS")) {
                                total ++;
                            }
                        }
                        // CHECK IF MOVE DOWN IS POSSIBLE
                        if(i + 3 < stringMatrix.size()) {
                            String result = "X";
                            for (int k = 1; k < 4; k++) {
                                result = result.concat(stringMatrix.get(i + k).get(j));
                            }
                            if(result.equals("XMAS")) {
                                total ++;
                            }
                        }
                        // CHECK IF MOVE LEFT IS POSSIBLE
                        if(j - 3 >= 0) {
                            String result = "X";
                            for (int k = 1; k < 4; k++) {
                                result = result.concat(stringMatrix.get(i).get(j - k));
                            }
                            if(result.equals("XMAS")) {
                                total ++;
                            }
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
