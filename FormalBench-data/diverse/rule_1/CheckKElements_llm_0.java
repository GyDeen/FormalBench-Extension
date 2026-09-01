
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckKElements {

	public static Boolean checkKElements(int[][] elements, int test) {
		for (int[] i : elements) {
			for (int array : i) {
				if (array != test) {
					return false;
				}
			}
		}
		return true;
	}
}
