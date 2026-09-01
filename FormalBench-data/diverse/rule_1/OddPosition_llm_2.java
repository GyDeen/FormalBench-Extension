
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddPosition {

	public static Boolean oddPosition(int[] positions) {
		int num = positions.length;
		for (int c = 0; c < num; c += 2) {
			if (positions[c] % 2 != 0) {
				return false;
			}
		}
		return true;
	}
}
