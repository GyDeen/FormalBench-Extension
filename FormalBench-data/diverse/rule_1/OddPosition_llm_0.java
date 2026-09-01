
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddPosition {

	public static Boolean oddPosition(int[] positions) {
		int result = positions.length;
		for (int c = 0; c < result; c += 2) {
			if (positions[c] % 2 != 0) {
				return false;
			}
		}
		return true;
	}
}
