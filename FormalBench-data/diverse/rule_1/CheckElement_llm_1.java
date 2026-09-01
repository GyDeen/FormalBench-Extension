
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckElement {

	public static Boolean checkElement(Object[] elements, Object object) {
		for (Object o : elements) {
			if (!compare(o, object)) {
				return false;
			}
		}
		return true;
	}

	public static boolean compare(Object Array, Object tmp) {
		if (Array == null && tmp == null) {
			return true;
		} else if (Array == null || tmp == null) {
			return false;
		} else {
			return Array.equals(tmp);
		}
	}
}
