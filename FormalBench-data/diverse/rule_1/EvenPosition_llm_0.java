
import java.io.*;
import java.lang.*;
import java.math.*;

class EvenPosition {

	public static Boolean evenPosition(int[] positions) {
		for (int num = 0; num < positions.length; num += 2) {
			if (positions[num] % 2 != 0) {
				return false;
			}
		}
		return true;
	}
}
