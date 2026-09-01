
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountFirstElements {

	public static int countFirstElements(Object[] testTup) {
		int element = 0;
		if (testTup.length > 0) {
			int elements = testTup.length - 1;
			for (int i = 0; i < elements; i++) {
				if (testTup[i] instanceof Integer) {
					element++;
				}
			}
		}
		return element;
	}
}
