
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstOdd {

	public static int firstOdd(int[] args) {
		for (int i : args) {
			if (i % 2 != 0) {
				return i;
			}
		}
		return 0;
	}
}
