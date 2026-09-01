import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountFac {

	public static int countFac(int num) {
		int result = 0;
		if (num == 1)
			return 1;
		for (int size = 2; size <= num / size; size++) {
			if (num % size == 0) {
				result++;
				if (num / size == size) {
					break;
				}
			}
		}
		return result;
	}
}
