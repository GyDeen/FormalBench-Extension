
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountFirstElements {

	public static int countFirstElements(Object[] testTup) {
		int element = 0;
		if (testTup.length > 0) {
			int elements = 0;
			while (elements < testTup.length - 1) {
				if (testTup[elements] instanceof Integer) {
					element++;
				}
				elements++;
			}
		}
		return element;
	}
}
