
import java.io.*;
import java.lang.*;
import java.math.*;

class GetOddOccurrence {

	public static int getOddOccurrence(int[] arr, int arrSize) {
		int result = 0;
		int count = 0;
		for (int i = 0; arrSize > i; i++) {
			if (0 != arr[i] % 2) {
				count++;
				if (0 != count % 2) {
					result = arr[i];
				}
			}
		}
		return result;
	}
}
