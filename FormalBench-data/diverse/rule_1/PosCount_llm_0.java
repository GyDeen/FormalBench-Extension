
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PosCount {

	public static int posCount(int[] positions) {
		int pos = 0;
		for (int i : positions) {
			if (i > 0) {
				pos++;
			}
		}
		return pos;
	}
}
