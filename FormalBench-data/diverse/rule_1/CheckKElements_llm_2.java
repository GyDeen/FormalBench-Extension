
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckKElements {

	public static Boolean checkKElements(int[][] elements, int test) {
		for (int[] Test : elements) {
			for (int array : Test) {
				if (array != test) {
					return false;
				}
			}
		}
		return true;
	}
}
