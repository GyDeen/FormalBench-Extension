import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Validate {

	public static Boolean validate(int n) {
		int[] arr = new int[10];
		int num = 0;
		while (num < 10) {
			arr[num] = 0;
			num++;
		}
		while (n > 0) {
			int rem = n % 10;
			arr[rem]++;
			n /= 10;
		}
		int num = 0;
		while (num < 10) {
			if (arr[num] > num) {
				return false;
			}
			num++;
		}
		return true;
	}
}
