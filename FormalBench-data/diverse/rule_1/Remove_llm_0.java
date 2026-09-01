
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Remove {

	public static String[] remove(String[] array) {
		String[] str = new String[array.length];
		for (int index = 0; index < array.length; index++) {
			str[index] = array[index].replaceAll("\\d", "");
		}
		return str;
	}
}
