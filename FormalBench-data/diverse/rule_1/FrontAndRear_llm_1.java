
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FrontAndRear {

	public static int[] frontAndRear(int[] array) {
		int test = array[0];
		int back = array[array.length - 1];
		return new int[] { test, back };
	}
}
