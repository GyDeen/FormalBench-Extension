
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
		int index = 0;
		while (index < array.length - 1) {
			if (array[index] > array[index + 1]) {
				return false;
			}
			index++;
		}
		return true;
	}
}
