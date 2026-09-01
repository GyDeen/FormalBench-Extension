import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Validate {

	public static Boolean validate(int n) {
		int[] arr = new int[10];
		int index = 0;
		while (index < 10) {
			arr[index] = 0;
			index++;
		}
		while (n > 0) {
			int rem = n % 10;
			arr[rem]++;
			n /= 10;
		}
		int index = 0;
		while (index < 10) {
			if (arr[index] > index) {
				return false;
			}
			index++;
		}
		return true;
	}
}
