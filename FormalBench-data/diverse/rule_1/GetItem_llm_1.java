
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetItem {

	public static String getItem(Object[] objects, int i) {
		if (i < 0) {
			i = objects.length + i;
		}
		return (String) objects[i];
	}
}
