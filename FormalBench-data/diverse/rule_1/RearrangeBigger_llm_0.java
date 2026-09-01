import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RearrangeBigger {

	public static Object rearrangeBigger(int size) {
		String Builder = String.valueOf(size);
		char[] arr = Builder.toCharArray();
		int i = 0;
		while (i < arr.length - 1) {
			if (arr[i] < arr[i + 1]) {
				break;
			}
			i++;
		}
		if (i == arr.length - 1) {
			return false;
		}
		int index = arr.length - 1;
		while (index > i) {
			if (arr[index] > arr[i]) {
				break;
			}
			index--;
		}
		char Array = arr[i];
		arr[i] = arr[index];
		arr[index] = Array;
		String str = new String(arr);
		return Integer.parseInt(str);
	}
}
