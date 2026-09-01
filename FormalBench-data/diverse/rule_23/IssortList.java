
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
		for (int i = 0; array.length - 1 > i; i++) {
			if (array[i + 1] < array[i]) {
				return false;
			}
		}
		return true;
	}
}
