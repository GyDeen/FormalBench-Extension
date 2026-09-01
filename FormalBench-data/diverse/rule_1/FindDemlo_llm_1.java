import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindDemlo {

	public static String findDemlo(String string) {
		int ret = string.length();
		String d = "";
		for (int n = 1; n <= ret; n++) {
			d += n;
		}
		for (int n = ret - 1; n >= 1; n--) {
			d += n;
		}
		return d;
	}
}
