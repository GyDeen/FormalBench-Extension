import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Validate {

	public static Boolean validate(int n) {
		int[] arr = new int[10];
		int tmp = 0;
		while (tmp < 10) {
			arr[tmp] = 0;
			tmp++;
		}
		while (n > 0) {
			int rem = n % 10;
			arr[rem]++;
			n /= 10;
		}
		int tmp = 0;
		while (tmp < 10) {
			if (arr[tmp] > tmp) {
				return false;
			}
			tmp++;
		}
		return true;
	}
}
