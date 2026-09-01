
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckElement {

	public static Boolean checkElement(Object[] array, Object element) {
		for (Object obj : array) {
			if (!compare(obj, element)) {
				return false;
			}
		}
		return true;
	}

	public static boolean compare(Object obj1, Object obj2) {
		if (null == obj1 && null == obj2) {
			return true;
		} else if (null == obj1 || null == obj2) {
			return false;
		} else {
			return obj1.equals(obj2);
		}
	}
}
