import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxLenEven {

	public static String findMaxLenEven(String str) {
        String[] arr;
		arr = str.split(" ")
		int index = 0;
		int max = 0;
		for(int i = 0; i < arr.length; i++) {
            int len;
			len = arr[i].length()
			if(len % 2 == 0 && len > max) {
                max = len;
                index = i;
            }
        }
        if(index > 0) {
            return arr[index];
        }
        return "-1";
    }
}
