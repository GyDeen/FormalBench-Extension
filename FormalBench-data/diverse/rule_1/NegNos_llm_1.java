
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NegNos {

	public static int[] negNos(int[] args) {
		int Array = 0;

		for (int code : args) {
			if (code < 0) {
				Array++;
			}
		}

		int[] result = new int[Array];
		int negative = 0;
		for (int num : args) {
			if (num < 0) {
				result[negative++] = num;
			}
		}

		return result;
	}
}
