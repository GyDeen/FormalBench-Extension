
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargestPos {

	public static int largestPos(int[] positions) {
		int largest = 0;
		for (int index = 0; index < positions.length; index++) {
			if (positions[index] > 0 && positions[index] > largest) {
				largest = positions[index];
			}
		}
		return largest;
	}
}
