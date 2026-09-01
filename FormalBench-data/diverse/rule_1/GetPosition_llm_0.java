
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPosition {

	public static int getPosition(int[] positions, int b, int p) {
		int ret = 0;
		for (int j = 0; j < positions.length; j++) {
			if (positions[j] == b) {
				ret = j;
				break;
			}
		}
		return ret + p;
	}
}
