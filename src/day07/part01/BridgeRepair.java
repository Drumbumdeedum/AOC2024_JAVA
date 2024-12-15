package day07.part01;

import java.util.*;
import java.io.*;

public class BridgeRepair {
    
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        
        // Read input from a file
        try (BufferedReader br = new BufferedReader(new FileReader("src/day07/input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                input.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            return;
        }
        
        long totalCalibrationResult = calculateTotalCalibrationResult(input.toArray(new String[0]));
        System.out.println("Total Calibration Result: " + totalCalibrationResult);
    }
    
    // Function to calculate the total calibration result
    public static long calculateTotalCalibrationResult(String[] equations) {
        long totalResult = 0;
        
        for (String equation : equations) {
            String[] parts = equation.split(": ");
            long targetValue = Long.parseLong(parts[0]);
            String[] numbers = parts[1].split(" ");
            
            long[] numArray = Arrays.stream(numbers).mapToLong(Long::parseLong).toArray();
            
            if (canEvaluateToTarget(numArray, targetValue)) {
                totalResult += targetValue;
            }
        }
        
        return totalResult;
    }
    
    // Function to check if the numbers can evaluate to the target value
    public static boolean canEvaluateToTarget(long[] numbers, long target) {
        return evaluate(numbers, 0, numbers[0], target);
    }
    
    // Recursive function to evaluate combinations of operators
    private static boolean evaluate(long[] numbers, int index, long current, long target) {
        if (index == numbers.length - 1) {
            return current == target;
        }
        
        long next = numbers[index + 1];
        
        // Try addition
        if (evaluate(numbers, index + 1, current + next, target)) {
            return true;
        }
        
        // Try multiplication
        if (evaluate(numbers, index + 1, current * next, target)) {
            return true;
        }
        
        // Try concatenation
        long concatenated = Long.parseLong(current + "" + next);
        if (evaluate(numbers, index + 1, concatenated, target)) {
            return true;
        }
        
        return false;
    }
}

