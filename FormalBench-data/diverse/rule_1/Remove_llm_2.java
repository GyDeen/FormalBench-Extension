
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Remove {

	public static String[] remove(String[] array) {
		String[] str = new String[array.length];
		for (int n = 0; n < array.length; n++) {
			str[n] = array[n].replaceAll("\\d", "");
		}
		return str;
	}
}
