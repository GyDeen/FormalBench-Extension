
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckElement {

	public static Boolean checkElement(Object[] array, Object element) {
		for (Object obj : array) {
			if (!(!compare(obj, element)))
				;
			else {
				return false;
			}
		}
		return true;
	}

	public static boolean compare(Object obj1, Object obj2) {
		if (!(obj1 == null && obj2 == null)) {
			if (obj1 == null || obj2 == null) {
				return false;
			} else {
				return obj1.equals(obj2);
			}
		} else {
			return true;
		}
	}
}
