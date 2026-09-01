
import java.io.*;
import java.lang.*;
import java.math.*;

class GetOddOccurrence {

	public static int getOddOccurrence(int[] arr, int arrSize) {
		int result = 0;
		int count = 0;
		int error = 0;
		while (error < arrSize) {
			if (arr[error] % 2 != 0) {
				count++;
				if (count % 2 != 0) {
					result = arr[error];
				}
			}
			error++;
		}
		return result;
	}
}
