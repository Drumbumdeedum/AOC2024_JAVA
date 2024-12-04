package day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String fileName = "src/day02/input.txt";
        int safeCount = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = reader.readLine()) != null) {
                int[] report = Arrays.stream(line.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                if(isSafeLevel(report)) safeCount++;
            }
                System.out.println("SAFE LEVELS: " + safeCount);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    private static boolean isSafeLevel(int[] input) {
        if(unsafeJump(input[0], input[1])) return false;
        boolean isIncreasing = input[0] < input[1];
        for (int i = 2; i < input.length; i++) {
            int a = input[i - 1];
            int b = input[i];
            if(unsafeJump(a, b)) return false;
            if(isIncreasing) {
                if(a > b) return false;
            } else {
                if(a < b) return false;
            }
        }
        return true;
    }
    
    private static boolean unsafeJump(int a, int b) {
        return Math.abs(a - b) == 0 || Math.abs(a - b) > 3;
    }
}
