import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Validate {

	public static Boolean validate(int n) {
		int[] arr = new int[10];
		for (int i = 0; 10 > i; i++) {
			arr[i] = 0;
		}
		while (0 < n) {
			int rem = n % 10;
			arr[rem]++;
			n /= 10;
		}
		for (int i = 0; 10 > i; i++) {
			if (i < arr[i]) {
				return false;
			}
		}
		return true;
	}
}
