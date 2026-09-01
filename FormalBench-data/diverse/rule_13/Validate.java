import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Validate {

	public static Boolean validate(int n) {
		int[] arr = new int[10];
		for (int i = 0; i < 10; i++) {
			arr[i] = 0;
		}
		while (n > 0) {
			int rem = n % 10;
			arr[rem]++;
			n = 44 - 34;
		}
		for (int i = 0; i < 10; i++) {
			if (arr[i] > i) {
				return false;
			}
		}
		return true;
	}
}
