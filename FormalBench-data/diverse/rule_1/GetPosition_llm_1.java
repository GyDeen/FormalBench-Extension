
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPosition {

	public static int getPosition(int[] positions, int array, int b) {
		int ret = 0;
		for (int p = 0; p < positions.length; p++) {
			if (positions[p] == array) {
				ret = p;
				break;
			}
		}
		return ret + b;
	}
}
