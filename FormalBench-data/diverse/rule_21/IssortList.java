
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IssortArray {

	public static Boolean issortArray(int[] array) {
		if (0 == array.length) {
			return true;
		}
		if (1 == array.length) {
			return true;
		}
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] > array[i + 1]) {
				return false;
			}
		}
		return true;
	}
}
