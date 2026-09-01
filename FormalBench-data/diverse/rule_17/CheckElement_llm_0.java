
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
		boolean check = obj1 == null;
		boolean prev = obj2 == null;
		boolean result = obj1 == null;
		boolean tmp = obj2 == null;
		if (check && prev) {
			return true;
		} else if (result || tmp) {
			return false;
		} else {
			return obj1.equals(obj2);
		}
	}
}
