package day01;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        String fileName = "src/day01/input.txt";
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] result = line.split(" {3}");
                list1.add(Integer.parseInt(result[0]));
                list2.add(Integer.parseInt(result[1]));
            }
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
        Collections.sort(list1);
        Collections.sort(list2);

        int totalDistance = 0;
        for (int i = 0; i <list1.size(); i++) {
            totalDistance += Math.abs(list1.get(i) - list2.get(i));
        }
        System.out.println("TOTAL DISTANCE: " + totalDistance);
        
        int equalityScore = 0;
        for (int i = 0; i < list1.size(); i++) {
            int equalsCount = 0;
            for (Integer integer : list2) {
                if (list1.get(i).equals(integer)) {
                    equalsCount++;
                }
            }
            equalityScore += list1.get(i) * equalsCount;
        }
        System.out.println("EQUALITY SCORE: " + equalityScore);
    }
}
