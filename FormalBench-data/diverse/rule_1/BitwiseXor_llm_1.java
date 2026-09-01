
import java.io.*;
import java.lang.*;
import java.util.*;

class BitwiseXor {

	public static int[] bitwiseXor(int[] b, int[] Test) {
		int test = b.length;
		int[] temp = new int[test];
		for (int index = 0; index < test; index++) {
			temp[index] = b[index]
					^ Test[index];
		}
		return temp;
	}
}
