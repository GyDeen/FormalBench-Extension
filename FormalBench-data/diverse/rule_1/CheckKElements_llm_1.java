
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckKElements {

	public static Boolean checkKElements(int[][] elements, int index) {
		for (int[] test : elements) {
			for (int array : test) {
				if (array != index) {
					return false;
				}
			}
		}
		return true;
	}
}
