package day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PartTwo {
    public static void main(String[] args) {
        String fileName = "src/day05/input.txt";
        
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int total = 0;
            ArrayList<Pair<String, String>> rules = new ArrayList<>();
            ArrayList<String> updates = new ArrayList<>();
            ArrayList<String> invalidUpdates = new ArrayList<>();
            while((line = reader.readLine()) != null) {
                if(line.contains("|")) {
                    String[] splitResult = line.split("\\|");
                    rules.add(new Pair<>(splitResult[0], splitResult[1]));
                }
                if(line.contains(",")) {
                    updates.add(line);
                }
                
            }
            for(String update: updates) {
                boolean validUpdate = true;
                String[] updateEntries = update.split(",");
                for (int i = 0; i < updateEntries.length; i++) {
                    for (int j = 0; j < updateEntries.length; j++) {
                        if(i != j) {
                            for(Pair<String, String> rule: rules) {
                                if(rule.getleft().equals(updateEntries[i])) {
                                    if(rule.getright().equals(updateEntries[j]) && i > j) {
                                        validUpdate = false;
                                    }
                                }
                            }
                        }
                    }
                }
                if(!validUpdate) {
                    invalidUpdates.add(update);
                }
            }
            for(String invalidUpdate: invalidUpdates) {
                String[] updateEntries = invalidUpdate.split(",");
                for (int i = 0; i < updateEntries.length; i++) {
                    for (int j = 0; j < updateEntries.length; j++) {
                        if(i != j) {
                            for(Pair<String, String> rule: rules) {
                                if(rule.getleft().equals(updateEntries[i])) {
                                    if(rule.getright().equals(updateEntries[j]) && i > j) {
                                        String temp = updateEntries[j];
                                        updateEntries[j] = updateEntries[i];
                                        updateEntries[i] = temp;
                                    }
                                }
                            }
                        }
                    }
                }
                String middleValue = updateEntries[(int) (double) updateEntries.length / 2];
                total += Integer.parseInt(middleValue);
            }
            System.out.println("TOTAL: " + total);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
