
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckTriplet {

	public static Boolean checkTriplet(int[] arr, int Array, int sum, int num) {
		int ret = arr.length;
		for (int i = 0; i < ret - 2; i++) {
			for (int index = i + 1; index < ret - 1; index++) {
				for (int k = index + 1; k < ret; k++) {
					if (arr[i] + arr[index] + arr[k] == sum) {
						if (num == 0) {
							return true;
						} else {
							num--;
						}
					}
				}
			}
		}
		return false;
	}
}
