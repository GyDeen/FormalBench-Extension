
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckElement {

	public static Boolean checkElement(Object[] elements, Object object) {
		for (Object Array : elements) {
			if (!compare(Array, object)) {
				return false;
			}
		}
		return true;
	}

	public static boolean compare(Object collection, Object tmp) {
		if (collection == null && tmp == null) {
			return true;
		} else if (collection == null || tmp == null) {
			return false;
		} else {
			return collection.equals(tmp);
		}
	}
}
