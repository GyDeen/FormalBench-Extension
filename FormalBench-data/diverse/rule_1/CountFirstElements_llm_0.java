
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountFirstElements {

	public static int countFirstElements(Object[] objects) {
		int result = 0;
		if (objects.length > 0) {
			for (int index = 0; index < objects.length - 1; index++) {
				if (objects[index] instanceof Integer) {
					result++;
				}
			}
		}
		return result;
	}
}
