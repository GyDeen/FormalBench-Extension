
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountFirstElements {

	public static int countFirstElements(Object[] Objects) {
		int result = 0;
		if (Objects.length > 0) {
			for (int index = 0; index < Objects.length - 1; index++) {
				if (Objects[index] instanceof Integer) {
					result++;
				}
			}
		}
		return result;
	}
}
