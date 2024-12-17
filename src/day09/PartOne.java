package day09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartOne {
    
    public static void main(String[] args) throws IOException {
        /*
          TEST MODE SWITCH
        */
        boolean testMode = true;
        String input = initInput(testMode);
        String[] inputArray = input.split("");
        int[] intArray = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            intArray[i] = Integer.parseInt(inputArray[i]);
        }
        String fileBlockString = "";
        int fileBlock = 0;
        for (int i = 0; i < intArray.length; i++) {
            int currentValue = intArray[i];
            for (int j = 0; j < currentValue; j++) {
                if(i % 2 == 0) {
                    fileBlockString = fileBlockString.concat(Integer.toString(fileBlock));
                } else {
                    fileBlockString = fileBlockString.concat(".");
                }
            }
            if(i % 2 == 0) {
                fileBlock++;
            }
        }
        String[] fileBlocks = fileBlockString.split("");
        while (swapPossible(fileBlocks)) {
            for (int i = 0; i < fileBlocks.length; i++) {
                if(i == fileBlocks.length - 1) break;
                if(fileBlocks[i].equals(".")) {
                    for (int j = fileBlocks.length - 1; j > 0 ; j--) {
                        if(!fileBlocks[j].equals(".")) {
                            String temp = fileBlocks[j];
                            fileBlocks[j] = fileBlocks[i];
                            fileBlocks[i] = temp;
                            System.out.println(Arrays.toString(fileBlocks));
                            break;
                        }
                    }
                    break;
                }
            }
        }
        
        System.out.println(Arrays.toString(fileBlocks));
        int result = 0;
        for (int i = 0; i < fileBlocks.length; i++) {
            if(fileBlocks[i].equals(".")) break;
            result += (Integer.parseInt(fileBlocks[i]) * i);
        }
        System.out.println(result);
    }
    
    public static boolean swapPossible(String[] fileBlocks) {
        String fileBlockString = "";
        for(String block: fileBlocks) {
            fileBlockString = fileBlockString.concat(block);
        }
        String[] splitString = fileBlockString.split("\\.", 2);
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(splitString[1]);
        return matcher.find();
    }
    
    public static String initInput(boolean testMode) throws IOException {
        String fileName = testMode ? "src/day09/day09_test_input.txt" : "src/day09/day09_input.txt";
        List<String> input = Files.readAllLines(Paths.get(fileName));
        return input.getFirst();
    }
}
