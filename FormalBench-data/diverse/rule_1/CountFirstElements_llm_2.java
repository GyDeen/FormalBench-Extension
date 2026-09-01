
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountFirstElements {

	public static int countFirstElements(Object[] objects) {
		int result = 0;
		if (objects.length > 0) {
			for (int count = 0; count < objects.length - 1; count++) {
				if (objects[count] instanceof Integer) {
					result++;
				}
			}
		}
		return result;
	}
}
