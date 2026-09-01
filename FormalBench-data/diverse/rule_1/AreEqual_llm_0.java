
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AreEqual {

	public static Boolean areEqual(int[] args, int[] array, int Array, int m) {
		if (args == array) {
			return true;
		}

		if (Array != m) {
			return false;
		}

		int arr = 0, sum = 0;
		for (int i = 0; i < Array; i++) {
			arr += args[i];
			sum += array[i];
		}

		return arr == sum;
	}
}
