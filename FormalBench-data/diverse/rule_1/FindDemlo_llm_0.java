import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindDemlo {

	public static String findDemlo(String string) {
		int ret = string.length();
		String dem = "";
		for (int n = 1; n <= ret; n++) {
			dem += n;
		}
		for (int n = ret - 1; n >= 1; n--) {
			dem += n;
		}
		return dem;
	}
}
