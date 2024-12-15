package day07.part01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static final boolean testMode = false;
    
    public static void main(String[] args) {
        HashMap<Long, Long[]> input = initValues();
        partOne(input);
    }
    
    public static void partOne(HashMap<Long, Long[]> input) {
        Long total = 0L;
        for (HashMap.Entry<Long, Long[]> entry : input.entrySet()) {
            Long[] values = entry.getValue();
            Set<Long> uniqueResults = new HashSet<>();
            
            //String[] operators = new String[]{"+", "*"};
            String[] operators = new String[]{"+", "*", "|"};
            int numOperators = values.length - 1;
            List<String> combinations = generateCombinations(operators, numOperators);
            System.out.println(combinations);
            for (String combination: combinations) {
                uniqueResults.add(calculateExpression(values, combination.split("")));
            }
            if(uniqueResults.contains(entry.getKey())) {
                System.out.println(entry.getKey() + " FOUND IN: " + uniqueResults);
                total += entry.getKey();
            }
        }
        System.out.println("RESULT SUM: " + total);
    }
    
    public static List<String> generateCombinations(String[] chars, int length) {
        List<String> combinations = new ArrayList<>();
        generateCombinationsRecursive(chars, "", length, combinations);
        return combinations;
    }
    
    private static void generateCombinationsRecursive(String[] chars, String current, int length, List<String> combinations) {
        if (current.length() == length) {
            combinations.add(current);
            return;
        }
        for (String ch : chars) {
            generateCombinationsRecursive(chars, current + ch, length, combinations);
        }
    }
    
    private static Long calculateExpression(Long[] numbers, String[] operators) {
        Long result = numbers[0];
        for (int i = 0; i < operators.length; i++) {
            if (Objects.equals(operators[i], "+")) {
                result += numbers[i + 1];
            } else if (Objects.equals(operators[i], "*")) {
                result *= numbers[i + 1];
            } else if (Objects.equals(operators[i], "|")) {
                result = Long.parseLong(result.toString().concat(numbers[i + 1].toString()));
            }
        }
        return result;
    }
    
    public static void printValues(HashMap<Long, Long[]> input) {
        for (HashMap.Entry<Long, Long[]> entry : input.entrySet()) {
            System.out.print(entry.getKey()+ ": ");
            for(Long value: entry.getValue()) {
                System.out.print(value + ", ");
            }
            System.out.println();
        }
    }
    
    public static HashMap<Long, Long[]> initValues() {
        String fileName = testMode ? "src/day07/test_input.txt" : "src/day07/input.txt";
        HashMap<Long, Long[]> result = new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            for (String line : lines) {
                String[] splitResult = line.split(" ");
                Long[] intArray = new Long[splitResult.length - 1];
                splitResult[0] = splitResult[0].substring(0, splitResult[0].length() - 1);
                for (int j = 0; j < splitResult.length - 1; j++) {
                    intArray[j] = Long.parseLong(splitResult[j + 1]);
                }
                result.put(Long.parseLong(splitResult[0]), intArray);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }
}
