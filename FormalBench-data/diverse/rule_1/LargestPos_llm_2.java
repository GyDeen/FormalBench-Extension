
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargestPos {

	public static int largestPos(int[] positions) {
		int largest = 0;
		for (int count = 0; count < positions.length; count++) {
			if (positions[count] > 0 && positions[count] > largest) {
				largest = positions[count];
			}
		}
		return largest;
	}
}
