
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Remove {

	public static String[] remove(String[] args) {
		String[] str = new String[args.length];
		for (int index = 0; index < args.length; index++) {
			str[index] = args[index].replaceAll("\\d", "");
		}
		return str;
	}
}
