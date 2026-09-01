
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PosCount {

	public static int posCount(int[] positions) {
		int pos = 0;
		for (int index : positions) {
			if (index > 0) {
				pos++;
			}
		}
		return pos;
	}
}
