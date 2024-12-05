package day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String fileName = "src/day03/input.txt";
        
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String input = "";
            
            while((line = reader.readLine()) != null) {
                input = input.concat(line);
            }
            System.out.println("RESULT: " + calcMultiplicationResultsInString(input));
            
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public static int calcMultiplicationResultsInString(String input) {
        int result = 0;
        String regex = "mul\\(\\d{1,3},\\d{1,3}\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while(matcher.find()) {
            String match = matcher.group();
            String regex2 = "\\d{1,3}";
            Pattern pattern2 = Pattern.compile(regex2);
            Matcher matcher2 = pattern2.matcher(match);
            ArrayList<String> matchesList = new ArrayList<>();
            while (matcher2.find()) {
                matchesList.add(matcher2.group());
            }
            result += (Integer.parseInt(matchesList.get(0)) * Integer.parseInt(matchesList.get(1)));
        }
        return result;
    }
}
