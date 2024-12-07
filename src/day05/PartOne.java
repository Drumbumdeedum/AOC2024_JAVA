package day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PartOne {
    public static void main(String[] args) {
        String fileName = "src/day05/testInput.txt";
        
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String input = "";
            while((line = reader.readLine()) != null) {
                input = input.concat(line);
            }
            
            System.out.println(input);
        
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
