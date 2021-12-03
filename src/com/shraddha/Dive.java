package com.shraddha;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dive {
    public static ArrayList<String> inputExtraction(File file) throws FileNotFoundException {
        ArrayList<String> data = new ArrayList<>();
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) data.add(sc.nextLine());

        return data;
    }

    public static int partOne(ArrayList<String> data) {
        int depth = 0;
        int position = 0;

        for (String element : data) {
            String[] valueOfLine = element.split(" ");
            if (valueOfLine[0].equals("forward")) {
                position += Integer.parseInt(valueOfLine[1]);

            } else if (valueOfLine[0].equals("down")) {
                depth += Integer.parseInt(valueOfLine[1]);

            } else {
                depth -= Integer.parseInt(valueOfLine[1]);
            }
        }
        return depth * position;
    }

    public static int partTwo(ArrayList<String> data) {
        int depth = 0;
        int position = 0;
        int aim = 0;

        for (String line : data) {
            String[] valueOfLine = line.split(" ");
//            System.out.println("position = " + position + "\n" + "Depth = " + depth + "\n" + "aim = " + aim + "\n" + "******");
            if (valueOfLine[0].equals("forward")) {
                position += Integer.parseInt(valueOfLine[1]);
                depth = depth + (aim * Integer.parseInt(valueOfLine[1]));
            } else if (valueOfLine[0].equals("down")) {
                aim += Integer.parseInt(valueOfLine[1]);
            } else {
                aim -= Integer.parseInt(valueOfLine[1]);
            }
        }
        return position * depth;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> data = inputExtraction(new File("src\\resources\\inputs\\day2.txt"));

        int partOneAns = partOne(data);
        System.out.println("Part One = " + partOneAns);

        int partTwoAns = partTwo(data);
        System.out.println("Part Two = " + partTwoAns);
    }
}
