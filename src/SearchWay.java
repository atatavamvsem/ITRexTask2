package com.company;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class SearchWay {

    static String[][][] inputArr;
    static int levels;
    static int rows;
    static int columns;
    static int res;
    static int way = 1;

    public SearchWay() {
        res = 0;
        try {
            FileReader reader = new FileReader("F:\\INPUT.TXT");
            Scanner in2 = new Scanner(reader);
            String firstLine = in2.nextLine();
            levels = parseInt(firstLine.substring(0, 1));
            rows = parseInt(firstLine.substring(1, 2));
            columns = parseInt(firstLine.substring(2, 3));
            inputArr = new String[levels][rows][columns];
            while (in2.hasNextLine()) {
                for (int level = 0; level < levels; level++) {
                    for (int row = 0; row < rows; ) {
                        String line = in2.nextLine();
                        if (!line.isEmpty()) {
                            char[] lineArr = line.toCharArray();
                            for (int col = 0; col < columns; col++) {
                                if (String.valueOf(lineArr[col]).equals("1")){
                                    inputArr[level][row][col] = String.valueOf("0");
                                }else if(String.valueOf(lineArr[col]).equals("2")){
                                    inputArr[level][row][col] = String.valueOf("b");
                                }else {
                                    inputArr[level][row][col] = String.valueOf(lineArr[col]);
                                }
                            }
                            row++;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SearchWay sw = new SearchWay();
        sw.findPath(inputArr, 0, way);
        System.out.println(res*5);
    }

    private void findPath(String[][][] inputArr, int stg, int way){
        while (res==0){
            for (int row = 0; row < rows; row++){
                for (int col = 0; col < columns; col++){
                    if(!inputArr[stg][row][col].equals("b")){
                        if(inputArr[stg][row][col].equals(String.valueOf(way-1))){
                            if (row+1 <= rows-1 && inputArr[stg][row+1][col].equals(".")){
                                inputArr[stg][row+1][col] = String.valueOf(way);
                            }
                            if (col+1 <= columns-1 && inputArr[stg][row][col+1].equals(".")){
                                inputArr[stg][row][col+1] = String.valueOf(way);
                            }
                            if (row-1 >= 0 && inputArr[stg][row-1][col].equals(".")){
                                inputArr[stg][row-1][col] = String.valueOf(way);
                            }
                            if (col-1 >= 0 && inputArr[stg][row][col-1].equals(".")){
                                inputArr[stg][row][col-1] = String.valueOf(way);
                            }
                            if (stg+1 < levels && inputArr[stg+1][row][col].equals("b")){
                                res = way;
                            }
                            if (stg+1 < levels && inputArr[stg+1][row][col].equals(".")){
                                inputArr[stg+1][row][col] = String.valueOf(way);
                                way++;
                                findPath(inputArr, stg+1, way);
                            }
                            way++;
                        }
                    } else {
                        way--;
                        res = way;
                    }
                }
            }
        }
    }
}
