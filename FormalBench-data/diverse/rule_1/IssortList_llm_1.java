
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IssortArray {

	public static Boolean issortArray(int[] arr) {
		if (arr.length == 0) {
			return true;
		}
		if (arr.length == 1) {
			return true;
		}
		for (int index = 0; index < arr.length - 1; index++) {
			if (arr[index] > arr[index + 1]) {
				return false;
			}
		}
		return true;
	}
}
