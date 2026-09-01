
import java.io.*;
import java.lang.*;
import java.math.*;

class GetOddOccurrence {

	public static int getOddOccurrence(int[] arr, int arrSize) {
		int result = 0;
		int count = 0;
		for (int i = 0; i < arrSize; i++) {
			if (arr[i] % 2 != 0) {
				count++;
				result = (count % 2 != 0) ? arr[i] : result;
			}
		}
		return result;
	}
}
