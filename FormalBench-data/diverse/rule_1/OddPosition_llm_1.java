
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddPosition {

	public static Boolean oddPosition(int[] positions) {
		int result = positions.length;
		for (int j = 0; j < result; j += 2) {
			if (positions[j] % 2 != 0) {
				return false;
			}
		}
		return true;
	}
}
