package com.shraddha;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BinaryDiagnostic {
    public static ArrayList<String> inputExtraction(File file) throws FileNotFoundException {
        ArrayList<String> data = new ArrayList<>();
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) data.add(sc.nextLine());

        return data;
    }

    public static int convertBinaryToInt(String binary) {
        return Integer.parseInt(binary, 2);
    }

    public static int partOne(ArrayList<String> data) {
        String mostCommonBits = "";
        String leastCommonBits = "";
        int countOnes, countZeros;

        for (int i = 0; i < data.get(0).length(); i++) {
            countOnes = 0;
            countZeros = 0;
            for (String val : data) {
                if (val.charAt(i) == '0') countZeros++;
                else countOnes++;
            }
            if (countZeros > countOnes) {
                mostCommonBits += '0';
                leastCommonBits += '1';
            } else {
                mostCommonBits += '1';
                leastCommonBits += '0';
            }
        }
        int least = convertBinaryToInt(leastCommonBits);
        int most = convertBinaryToInt(mostCommonBits);

        return least * most;
    }

    public static ArrayList<String> getData(ArrayList<String> data, int position, char toCheck) {
        ArrayList<String> temp = new ArrayList<>();

        for (String val : data) {
            if (val.charAt(position) == toCheck) temp.add(val);
        }
        return temp;
    }

    public static char getMostFrequentElement(ArrayList<String> data, int position, boolean isOxygen) {
        int numberOfOnes = 0, numberOfZeros = 0;
        for (String val : data) {
            if (val.charAt(position) == '1') numberOfOnes++;
            else numberOfZeros++;
        }
        if (isOxygen) {
            if (numberOfOnes >= numberOfZeros) return '1';
            return '0';
        }
        if (numberOfZeros <= numberOfOnes) return '0';
        return '1';
    }

    public static String oxygenGeneratorRating(ArrayList<String> data) {
        int len = data.get(0).length();

        for (int i = 0; i < len; i++) {
            char ch = getMostFrequentElement(data, i, true);
            data = getData(data, i, ch);
            if (data.size() == 1) break;
        }
        return data.get(0);
    }

    public static String CO2ScrubberRating(ArrayList<String> data) {
        int len = data.get(0).length();

        for (int i = 0; i < len; i++) {
            char ch = getMostFrequentElement(data, i, false);
            data = getData(data, i, ch);
            if (data.size() == 1) break;
        }
        return data.get(0);
    }

    public static int partTwo(ArrayList<String> data) {
        String oxygen = oxygenGeneratorRating(data);
        String co2 = CO2ScrubberRating(data);

        int oxygenInDecimal = convertBinaryToInt(oxygen);
        int co2InDecimal = convertBinaryToInt(co2);
        return oxygenInDecimal * co2InDecimal;

    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> data = inputExtraction(new File("src\\resources\\inputs\\day3.txt"));

        int partOneAns = partOne(data);
        System.out.println("Part One = " + partOneAns);

        int partTwoAns = partTwo(data);
        System.out.println("Part Two = " + partTwoAns);
    }
}
