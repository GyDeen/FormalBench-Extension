
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FrequencyOfSmallest {
    
    public static int frequencyOfSmallest(int n, int[] arr) {
        if (n == 0) {
            return 0;
        }

        int min = arr[0];
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (min > arr[i]) {
                min = arr[i];
                count = 1;
            } else if (min == arr[i]) {
                count++;
            }
        }
        return count;
    }
}

