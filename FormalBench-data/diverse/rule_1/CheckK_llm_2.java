
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckK {

	public static Boolean checkK(int[] arr, int test) {
		for (int index : arr) {
			if (index == test) {
				return true;
			}
		}
		return false;
	}
}
