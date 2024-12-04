package day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String fileName = "src/day02/input.txt";
        int safeCountStrict = 0;
        int safeCountRelaxed = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = reader.readLine()) != null) {
                int[] report = Arrays.stream(line.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                if(isSafeLevelStrict(report)) safeCountStrict++;
                if(isSafeLevelRelaxed(report)) safeCountRelaxed++;
                System.out.println("--------------------------------------------------------");
                System.out.println((isSafeLevelStrict(report) ? "SAFE IN STRICT MODE " : "UNSAFE IN STRICT MODE ") + Arrays.toString(report));
                System.out.println((isSafeLevelRelaxed(report) ? "SAFE IN RELAXED MODE " : "UNSAFE IN RELAXED MODE ") + Arrays.toString(report));
            }
            System.out.println("SAFE LEVELS STRICT MODE: " + safeCountStrict);
            System.out.println("SAFE LEVELS RELAXED MODE: " + safeCountRelaxed);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    private static boolean isSafeLevelStrict(int[] input) {
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
    
    private static boolean isSafeLevelRelaxed(int[] input) {
        if(isSafeLevelStrict(input)) return true;
        for (int i = 0; i < input.length; i++) {
            if(isSafeLevelStrict(removeByIndex(input, i))) return true;
        }
        return false;
    }
    
    private static boolean unsafeJump(int a, int b) {
        return Math.abs(a - b) == 0 || Math.abs(a - b) > 3;
    }
    
    public static int[] removeByIndex(int[] array, int indexToRemove) {
        if (indexToRemove < 0 || indexToRemove >= array.length) {
            throw new IllegalArgumentException("Index out of bounds.");
        }
        int[] result = new int[array.length - 1];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i != indexToRemove) {
                result[j++] = array[i];
            }
        }
        return result;
    }
}
