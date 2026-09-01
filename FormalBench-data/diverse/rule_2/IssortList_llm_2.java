
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
		int i = 0;
		while (i < array.length - 1) {
			if (array[i] > array[i + 1]) {
				return false;
			}
			i++;
		}
		return true;
	}
}
