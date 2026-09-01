
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstOdd {

	public static int firstOdd(int[] arr) {
		for (int index : arr) {
			if (index % 2 != 0) {
				return index;
			}
		}
		return 0;
	}
}
