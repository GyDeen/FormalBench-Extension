
import java.io.*;
import java.lang.*;
import java.math.*;

class GetOddOccurrence {

	public static int getOddOccurrence(int[] arr, int arrSize) {
		int result = 0;
		int count = 0;
		int max = 0;
		while (max < arrSize) {
			if (arr[max] % 2 != 0) {
				count++;
				if (count % 2 != 0) {
					result = arr[max];
				}
			}
			max++;
		}
		return result;
	}
}
