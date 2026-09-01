
import java.io.*;
import java.lang.*;
import java.math.*;

class GetOddOccurrence {

	public static int getOddOccurrence(int[] arr, int arrSize) {
		int result = 0;
		int count = 0;
		int total = 0;
		while (total < arrSize) {
			if (arr[total] % 2 != 0) {
				count++;
				if (count % 2 != 0) {
					result = arr[total];
				}
			}
			total++;
		}
		return result;
	}
}
