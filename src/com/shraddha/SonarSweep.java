package com.shraddha;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SonarSweep {
    public static ArrayList<Integer> inputExtraction(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        ArrayList<Integer> data = new ArrayList<>();

        while (sc.hasNextLine()) data.add(sc.nextInt());

        return data;
    }

    public static ArrayList<Integer> getSlidingWindowData(ArrayList<Integer> data){
        int windowSize = 3;
        ArrayList<Integer> slidingWindowData = new ArrayList<>(data.size()-windowSize+1);
        int val ;
        int count ;

        for (int i = 0; i <= data.size()-windowSize ; i++) {
            val = 0;
            count = windowSize;
            while (count!=0){
                val+=data.get(i+(count-1));
                count--;
            }
            slidingWindowData.add(val);
        }
        return slidingWindowData;
    }

    public static int partTwo(ArrayList<Integer> data){
        ArrayList<Integer> slidingWindowData = getSlidingWindowData(data);
        int count = 0;
        for (int i = 1; i <slidingWindowData.size() ; i++) {
            if (slidingWindowData.get(i)>slidingWindowData.get(i-1)) count++;
        }
        return count;
    }

    public static int partOne(ArrayList<Integer> input){
        int count = 0;
        for (int i = 1; i < input.size(); i++) {
            if (input.get(i) > input.get(i-1)) count++;
        }
        return count;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> data = inputExtraction(new File("src\\resources\\inputs\\day1.txt"));
        int partOneAns = partOne(data);
        System.out.println("Part one = "+partOneAns);
        int partTwoAns = partTwo(data);
        System.out.println("Part Two = "+partTwoAns);
    }
}
