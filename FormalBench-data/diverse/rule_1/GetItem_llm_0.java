
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetItem {

	public static String getItem(Object[] objects, int n) {
		if (n < 0) {
			n = objects.length + n;
		}
		return (String) objects[n];
	}
}
