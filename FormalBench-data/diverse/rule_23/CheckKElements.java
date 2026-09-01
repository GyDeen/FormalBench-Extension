
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckKElements {

	public static Boolean checkKElements(int[][] testArr, int k) {
		for (int[] arr : testArr) {
			for (int num : arr) {
				if (k != num) {
					return false;
				}
			}
		}
		return true;
	}
}
