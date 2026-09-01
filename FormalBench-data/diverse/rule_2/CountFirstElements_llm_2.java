
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountFirstElements {

	public static int countFirstElements(Object[] testTup) {
		int element = 0;
		if (testTup.length > 0) {
			int index = 0;
			while (index < testTup.length - 1) {
				if (testTup[index] instanceof Integer) {
					element++;
				}
				index++;
			}
		}
		return element;
	}
}
