
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetItem {

	public static String getItem(Object[] tup1, int index) {
		index = (index < 0) ? tup1.length + index : index;
		return (String) tup1[index];
	}
}
