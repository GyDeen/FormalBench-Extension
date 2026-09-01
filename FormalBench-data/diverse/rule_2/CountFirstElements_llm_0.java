
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountFirstElements {

	public static int countFirstElements(Object[] testTup) {
		int element = 0;
		if (testTup.length > 0) {
			int count = 0;
			while (count < testTup.length - 1) {
				if (testTup[count] instanceof Integer) {
					element++;
				}
				count++;
			}
		}
		return element;
	}
}
