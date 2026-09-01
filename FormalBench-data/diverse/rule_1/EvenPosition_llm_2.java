
import java.io.*;
import java.lang.*;
import java.math.*;

class EvenPosition {

	public static Boolean evenPosition(int[] position) {
		for (int num = 0; num < position.length; num += 2) {
			if (position[num] % 2 != 0) {
				return false;
			}
		}
		return true;
	}
}
