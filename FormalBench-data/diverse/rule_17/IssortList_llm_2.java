
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IssortArray {

	public static Boolean issortArray(int[] array) {
		if (array.length == 0) {
			return true;
		}
		if (array.length == 1) {
			return true;
		}
		int len = array.length - 1;
		for (int i = 0; i < len; i++) {
			int tmp = i + 1;
			if (array[i] > array[tmp]) {
				return false;
			}
		}
		return true;
	}
}
