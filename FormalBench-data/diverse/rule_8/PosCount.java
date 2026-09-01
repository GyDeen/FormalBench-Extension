
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PosCount {

	public static int posCount(int[] array) {
		int count = 0;
		for (int num : array) {
			if (num > 0) {
				count += 1;
			}
		}
		return count;
	}
}
