
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetGcd {

	public static int getGcd(int[] arr) {
		int result = 0;
		int min = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > min) {
				if (result > min)
					result = result;
				else
					result = min;
			} else {
				min = arr[i];
			}
		}
		return result;
	}
}
