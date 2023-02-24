package com.example.project;

import java.io.*;
import java.util.*;

public class Rubiks {

    private boolean dbg;

    private String[][] cube = new String[26][26];

    // private int[][] posMap = {
    //     3,  5,  7
    //     17, 25, 18
    //     1,  11, 5
                
    //     7,  13, 6
    //     18, 21, 19
    //     5,  9,  4
                
    //     6,  14, 2
    //     19, 24, 16
    //     4,  10, 0
                
    //     2,  12, 3
    //     16, 20, 17
    //     0,  8,  1
                
    //     0,  8,  1
    //     10, 23, 11
    //     4,  9,  5
                
    //     6,  13, 7
    //     14, 22, 15
    //     2,  12, 3
    // };
    private Hashtable<String, Integer> dirIdMap = new Hashtable<>();
    private String[] dirID = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x"};
    

    private String[][] dirMap = {
        {"a",	"b",	"c",	"d",	"e",	"f",	"g",	"h",	"i",	"j",	"k",	"l",	"m",	"n",	"o",	"p",	"q",	"r",	"s",	"t",	"u",	"v",	"w",	"x"},
        {"b",	"a",	"d",	"c",	"h",	"v",	"x",	"e",	"u",	"w",	"p",	"o",	"r",	"q",	"l",	"k",	"n",	"m",	"t",	"s",	"i",	"f",	"j",	"g"},
        {"c",	"d",	"a",	"b",	"s",	"i",	"w",	"t",	"f",	"x",	"l",	"k",	"q",	"r",	"p",	"o",	"m",	"n",	"e",	"h",	"v",	"u",	"g",	"j"},
        {"d",	"c",	"b",	"a",	"t",	"u",	"j",	"s",	"v",	"g",	"o",	"p",	"n",	"m",	"k",	"l",	"r",	"q",	"h",	"e",	"f",	"i",	"x",	"w"},
        {"e",	"h",	"t",	"s",	"b",	"n",	"k",	"a",	"m",	"l",	"x",	"w",	"u",	"v",	"j",	"g",	"f",	"i",	"c",	"d",	"r",	"q",	"o",	"p"},
        {"f",	"u",	"i",	"v",	"k",	"c",	"q",	"o",	"a",	"n",	"s",	"e",	"g",	"x",	"t",	"h",	"w",	"j",	"l",	"p",	"d",	"b",	"m",	"r"},
        {"g",	"w",	"x",	"j",	"m",	"k",	"d",	"q",	"p",	"a",	"u",	"i",	"t",	"e",	"f",	"v",	"s",	"h",	"r",	"n",	"o",	"l",	"c",	"b"},
        {"h",	"e",	"s",	"t",	"a",	"q",	"p",	"b",	"r",	"o",	"g",	"j",	"i",	"f",	"w",	"x",	"v",	"u",	"d",	"c",	"m",	"n",	"l",	"k"},
        {"i",	"v",	"f",	"u",	"l",	"a",	"m",	"p",	"c",	"r",	"e",	"s",	"w",	"j",	"h",	"t",	"g",	"x",	"k",	"o",	"b",	"d",	"q",	"n"},
        {"j",	"x",	"w",	"g",	"n",	"o",	"a",	"r",	"l",	"d",	"f",	"v",	"e",	"t",	"u",	"i",	"h",	"s",	"q",	"m",	"k",	"p",	"b",	"c"},
        {"k",	"o",	"p",	"l",	"u",	"x",	"s",	"f",	"g",	"e",	"r",	"m",	"d",	"b",	"n",	"q",	"c",	"a",	"i",	"v",	"j",	"w",	"t",	"h"},
        {"l",	"p",	"o",	"k",	"v",	"j",	"e",	"i",	"w",	"s",	"n",	"q",	"b",	"d",	"r",	"m",	"a",	"c",	"f",	"u",	"x",	"g",	"h",	"t"},
        {"m",	"q",	"n",	"r",	"w",	"e",	"u",	"g",	"t",	"i",	"b",	"c",	"o",	"l",	"a",	"d",	"k",	"p",	"x",	"j",	"h",	"s",	"f",	"v"},
        {"n",	"r",	"m",	"q",	"x",	"t",	"f",	"j",	"e",	"v",	"c",	"b",	"k",	"p",	"d",	"a",	"o",	"l",	"w",	"g",	"s",	"h",	"u",	"i"},
        {"o",	"k",	"l",	"p",	"f",	"w",	"h",	"u",	"j",	"t",	"q",	"n",	"a",	"c",	"m",	"r",	"b",	"d",	"v",	"i",	"g",	"x",	"e",	"s"},
        {"p",	"l",	"k",	"o",	"i",	"g",	"t",	"v",	"x",	"h",	"m",	"r",	"c",	"a",	"q",	"n",	"d",	"b",	"u",	"f",	"w",	"j",	"s",	"e"},
        {"q",	"m",	"r",	"n",	"g",	"s",	"v",	"w",	"h",	"f",	"d",	"a",	"p",	"k",	"c",	"b",	"l",	"o",	"j",	"x",	"t",	"e",	"i",	"u"},
        {"r",	"n",	"q",	"m",	"j",	"h",	"i",	"x",	"s",	"u",	"a",	"d",	"l",	"o",	"b",	"c",	"p",	"k",	"g",	"w",	"e",	"t",	"v",	"f"},
        {"s",	"t",	"h",	"e",	"d",	"r",	"l",	"c",	"q",	"k",	"j",	"g",	"v",	"u",	"x",	"w",	"i",	"f",	"a",	"b",	"n",	"m",	"p",	"o"},
        {"t",	"s",	"e",	"h",	"c",	"m",	"o",	"d",	"n",	"p",	"w",	"x",	"f",	"i",	"g",	"j",	"u",	"v",	"b",	"a",	"q",	"r",	"k",	"l"},
        {"u",	"f",	"v",	"i",	"o",	"b",	"r",	"k",	"d",	"m",	"h",	"t",	"j",	"w",	"e",	"s",	"x",	"g",	"p",	"l",	"a",	"c",	"n",	"q"},
        {"v",	"i",	"u",	"f",	"p",	"d",	"n",	"l",	"b",	"q",	"t",	"h",	"x",	"g",	"s",	"e",	"j",	"w",	"o",	"k",	"c",	"a",	"r",	"m"},
        {"w",	"g",	"j",	"x",	"q",	"l",	"b",	"m",	"o",	"c",	"v",	"f",	"h",	"s",	"i",	"u",	"e",	"t",	"n",	"r",	"p",	"k",	"a",	"d"},
        {"x",	"j",	"g",	"w",	"r",	"p",	"c",	"n",	"k",	"b",	"i",	"u",	"s",	"h",	"v",	"f",	"t",	"e",	"m",	"q",	"l",	"o",	"d",	"a"},
    };

    private int[][] dispMap = {
        {0, 6, 5, 3, 2, 4, 2, 4, 1, 1, 6, 3, 3, 0, 5, 0, 6, 5, 7, 1, 7, 2, 0, 0},
        {1, 7, 4, 2, 3, 0, 0, 5, 5, 3, 2, 7, 1, 2, 1, 4, 4, 7, 6, 0, 3, 6, 5, 6},
        {2, 4, 7, 1, 6, 6, 3, 0, 3, 0, 7, 2, 7, 4, 4, 1, 2, 1, 3, 5, 5, 0, 6, 5},
        {3, 5, 6, 0, 7, 2, 1, 1, 7, 2, 3, 6, 5, 6, 0, 5, 0, 3, 2, 4, 1, 4, 4, 7},
        {4, 2, 1, 7, 0, 5, 6, 6, 0, 5, 4, 1, 2, 1, 7, 2, 7, 4, 5, 3, 6, 3, 3, 0},
        {5, 3, 0, 6, 1, 1, 4, 7, 4, 7, 0, 5, 0, 3, 3, 6, 5, 6, 4, 2, 2, 7, 1, 2},
        {6, 0, 3, 5, 4, 7, 7, 2, 2, 4, 5, 0, 6, 5, 6, 3, 3, 0, 1, 7, 4, 1, 2, 1},
        {7, 1, 2, 4, 5, 3, 5, 3, 6, 6, 1, 4, 4, 7, 2, 7, 1, 2, 0, 6, 0, 5, 0, 3},
        {8, 13, 9, 12, 12, 10, 16, 9, 11, 17, 14, 15, 17, 16, 11, 10, 19, 18, 13, 8, 15, 14, 18, 19},
        {9, 12, 8, 13, 8, 11, 19, 13, 10, 18, 10, 11, 16, 17, 15, 14, 18, 19, 9, 13, 14, 15, 17, 16},
        {10, 14, 11, 15, 16, 9, 14, 19, 8, 11, 19, 17, 12, 8, 18, 16, 13, 9, 18, 17, 13, 12, 15, 10},
        {11, 15, 10, 14, 17, 8, 10, 18, 9, 15, 16, 18, 8, 12, 17, 19, 9, 13, 19, 16, 12, 13, 11, 14},
        {12, 9, 13, 8, 13, 14, 17, 8, 15, 16, 15, 14, 18, 19, 10, 11, 16, 17, 12, 9, 11, 10, 19, 18},
        {13, 8, 12, 9, 9, 15, 18, 12, 14, 19, 11, 10, 19, 18, 14, 15, 17, 16, 8, 13, 10, 11, 16, 17},
        {14, 10, 15, 11, 19, 13, 15, 16, 12, 10, 18, 16, 13, 9, 19, 17, 12, 8, 17, 18, 9, 8, 14, 11},
        {15, 11, 14, 10, 18, 12, 11, 17, 13, 14, 17, 19, 9, 13, 16, 18, 8, 12, 16, 19, 8, 9, 10, 15},
        {16, 19, 18, 17, 14, 19, 12, 10, 17, 8, 13, 12, 15, 10, 9, 8, 14, 11, 15, 11, 18, 16, 13, 9},
        {17, 18, 19, 16, 15, 16, 8, 11, 18, 12, 12, 13, 11, 14, 8, 9, 10, 15, 14, 10, 17, 19, 9, 13},
        {18, 17, 16, 19, 11, 17, 9, 15, 19, 13, 8, 9, 10, 15, 12, 13, 11, 14, 10, 14, 16, 18, 8, 12},
        {19, 16, 17, 18, 10, 18, 13, 14, 16, 9, 9, 8, 14, 11, 13, 12, 15, 10, 11, 15, 19, 17, 12, 8},
        {20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20},
        {21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21},
        {22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22},
        {23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23},
        {24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24},
        {25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25}
    };
    

    private Hashtable<String, Integer> moveIdMap = new Hashtable<>();
    private String[] moveID =  {"u", "d", "r", "l", "f", "b", "u'", "d'", "r'", "l'", "f'", "b'"};
    private String[] moveDir = {"i", "f", "e", "h", "j", "g", "f",  "i",  "h",  "e",  "g",  "j"};
    

    

    private int[][] moveBlocks = {
        {0,  8, 1, 10, 23, 11, 4,  9, 5},
        {6, 13, 7, 14, 22, 15, 2, 12, 3},
        {3, 15, 7, 17, 25, 18, 1, 11, 5},
        {6, 14, 2, 19, 24, 16, 4, 10, 0},
        {7, 13, 6, 18, 21, 19, 5,  9, 4},
        {2, 12, 3, 16, 20, 17, 0, 8, 1}
    };


    private int[] positions = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};

    public Rubiks(boolean dbg) {
        this.dbg = dbg;

        this.initializeCube();
        this.initMatrix();
        this.debug("created cube");

        this.debug(this.dirMultiply("b", "c"));
        this.debug(this.dirMultiply("d", "f"));
        this.debug(this.dirMultiply("h", "g"));

    }

    public void move(String moveDirection) {
        int moveIndex = this.moveIdMap.get(moveDirection);
        int dirIndex = moveIndex;
        if (moveIndex > 5) {
            dirIndex -= 6;
        }

        this.debug("Doing move: " + moveDirection);
        this.debug("Mapped to index: " + Integer.toString(moveIndex));

        this.debug("Effects blocks: ");

        this.debug("Loc    Blck   dir    mult    res   nLoc");



        //position[cubePosition] = blockId;

        int[][] newData = new int[9][2];
        
        for (int i = 0; i < 9; i++) {
            // the position of the block to move
            int cubePos = this.moveBlocks[dirIndex][i];
            // the block in that position
            int block = this.positions[cubePos];
            System.out.printf("%4s   %4s   ", cubePos, block);

            //orientation of block to move
            String dir = this.cube[block][cubePos];

            String mult = this.moveDir[moveIndex];
            String res = this.dirMultiply(dir, mult);



            // this.debug("dir: " + this.cube[block][position]);
            // this.debug("dir: " + this.cube[position][block]);

            

            System.out.printf("%4s   %4s   %4s   ", dir, mult, res);

            // this.debug(res);
            int newCubePos = this.dispMap[block][this.dirIdMap.get(res)];

            System.out.printf("%4s%n", newCubePos);

            this.cube[block][cubePos] = "";

            this.cube[block][newCubePos] = res;

            newData[i][0] = newCubePos;
            newData[i][1] = block;

            // System.out.printf("Position %s has block %s%n", position, newPos);
            // this.debug("\n\r");
            // this.printCube();
            
        }

        for (int i = 0; i < newData.length; i++) {
            this.positions[newData[i][0]] = newData[i][1];
        }
        







        // System.out.println("FROM TO");
        // for (int i = 0;i < this.positions.length; i++) {
        //     System.out.printf("%2s  %2s%n", i, this.positions[i]);
        // }

        // for (int i = 0; i < 9; i++) {
        //     int block = this.moveBlocks[dirIndex][i];
        //     int pos = this.positions[block];

        //     String dir = this.cube[block][pos];
        //     String mult = this.moveDir[moveIndex];
        //     String res = this.dirMultiply(dir, mult);

        //     int newPos = this.dispMap[block][this.dirIdMap.get(res)];

        //     this.cube[block][pos] = "";
        //     this.cube[block][newPos] = res;

        //     this.positions[block] = newPos;

        //     System.out.printf("%4s   %4s   %4s   %4s   %4s   %4s%n", block, pos, newPos, dir, mult, res);
        // }



        this.debug("\n\r");
        this.printCube();
    }

    private void initMatrix() {
        for (int l = 0; l < this.dirID.length; l++) {
            this.dirIdMap.put(dirID[l], l);
        }

        for (int l = 0; l < this.moveID.length; l++) {
            this.moveIdMap.put(moveID[l], l);
        }
    }

    private String dirMultiply(String a, String b) {
        return dirMap[this.dirIdMap.get(b)][this.dirIdMap.get(a)];
    }

    

    private void initializeCube() {
        for(int y = 0; y < 26; y++){
            for(int x = 0; x < 26; x++) {
                this.cube[y][x] = "";
            }
        }

        int y = 0;
        for(int x = 0; x < 26; x++) {
            this.cube[y][x] = "a";
            y++;
        }
        // this.printCube();
    }

    private void printCube() {
        System.out.print("    ");
        for(int x = 0; x < 26; x++) {
            System.out.printf("%2s|", x);
        }
        System.out.println();

        for(int y = 0; y < 26; y++) {
            System.out.printf("%2s  ", y);
            for(int x = 0; x < 26; x++) {
                System.out.printf("%2s|", this.cube[y][x]);
            }
            System.out.println();
        }
    }

    private void debug(String msg) {
        if (this.dbg == true) {
            System.out.println(msg);
        }
    }
}
