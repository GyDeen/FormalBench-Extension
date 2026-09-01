
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PosCount {

	public static int posCount(int[] positions) {
		int result = 0;
		for (int i : positions) {
			if (i > 0) {
				result++;
			}
		}
		return result;
	}
}
