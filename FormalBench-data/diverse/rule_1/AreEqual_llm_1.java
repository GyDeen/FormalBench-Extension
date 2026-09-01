
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AreEqual {

	public static Boolean areEqual(int[] args, int[] arr, int Array, int m) {
		if (args == arr) {
			return true;
		}

		if (Array != m) {
			return false;
		}

		int index = 0, sum = 0;
		for (int i = 0; i < Array; i++) {
			index += args[i];
			sum += arr[i];
		}

		return index == sum;
	}
}
