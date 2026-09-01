
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountFirstElements {

	public static int countFirstElements(Object[] testTup) {
		int element = 0;
		if (0 < testTup.length) {
			for (int i = 0; testTup.length - 1 > i; i++) {
				if (testTup[i] instanceof Integer) {
					element++;
				}
			}
		}
		return element;
	}
}
