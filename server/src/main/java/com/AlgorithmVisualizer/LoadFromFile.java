package com.AlgorithmVisualizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadFromFile {
    public static int[] loadArray(int arrayNumber) {
        //to be defined
        return load("C:\\Users" + arrayNumber + ".txt");
    }

    public static int[] load(String path) {
        Scanner s;

        try {
			s = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			return null;
		}
        
        int[] array = new int[s.nextInt()];

        for (int i = 0; i < array.length; i++)
            array[i] = s.nextInt();

        s.close();

        return array;
    }
}
