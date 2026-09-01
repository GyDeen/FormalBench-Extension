
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
		for (int Array = 0; Array < arr.length - 1; Array++) {
			if (arr[Array] > arr[Array + 1]) {
				return false;
			}
		}
		return true;
	}
}
