
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountingSort {
    
    public static int[] countingSort(int[] myArray) {
        if (myArray.length == 0) {
            return new int[0];
        }
        
        int max = myArray[0];
        int min = myArray[0];

        for (int num : myArray) {
            if (num > max) max = num;
            if (num < min) min = num;
        }

        int range = max - min + 1;
        int[] countArray = new int[range];

        for (int i = 0; i < myArray.length; i++) {
            countArray[myArray[i] - min]++;
        }

        int index = 0;
        int[] result = new int[myArray.length];

        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                result[index++] = i + min;
            }
        }
        return result;
    }
}

