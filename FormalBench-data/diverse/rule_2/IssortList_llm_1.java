
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
		int len = 0;
		while (len < array.length - 1) {
			if (array[len] > array[len + 1]) {
				return false;
			}
			len++;
		}
		return true;
	}
}
