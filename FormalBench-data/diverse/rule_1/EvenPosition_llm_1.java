
import java.io.*;
import java.lang.*;
import java.math.*;

class EvenPosition {

	public static Boolean evenPosition(int[] positions) {
		for (int index = 0; index < positions.length; index += 2) {
			if (positions[index] % 2 != 0) {
				return false;
			}
		}
		return true;
	}
}
