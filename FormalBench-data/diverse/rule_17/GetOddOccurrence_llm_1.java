
import java.io.*;
import java.lang.*;
import java.math.*;

class GetOddOccurrence {

	public static int getOddOccurrence(int[] arr, int arrSize) {
		int result = 0;
		int count = 0;
		for (int i = 0; i < arrSize; i++) {
			int j = arr[i] % 2;
			if (j != 0) {
				count++;
				int index = count % 2;
				if (index != 0) {
					result = arr[i];
				}
			}
		}
		return result;
	}
}
