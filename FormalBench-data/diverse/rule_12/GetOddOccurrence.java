
import java.io.*;
import java.lang.*;
import java.math.*;

class GetOddOccurrence {

	public static int getOddOccurrence(int[] arr, int arrSize) {
		int count = 0;
		int result = 0;
		for (int i = 0; i < arrSize; i++) {
			if (arr[i] % 2 != 0) {
				count++;
				if (count % 2 != 0) {
					result = arr[i];
				}
			}
		}
		return result;
	}
}
