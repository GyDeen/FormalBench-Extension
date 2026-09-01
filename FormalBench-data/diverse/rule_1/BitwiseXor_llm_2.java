
import java.io.*;
import java.lang.*;
import java.util.*;

class BitwiseXor {

	public static int[] bitwiseXor(int[] b, int[] Test) {
		int arr = b.length;
		int[] test = new int[arr];
		for (int index = 0; index < arr; index++) {
			test[index] = b[index]
					^ Test[index];
		}
		return test;
	}
}
