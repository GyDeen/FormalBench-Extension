import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindDemlo {

	public static String findDemlo(String string) {
		int ret = string.length();
		String dem = "";
		for (int r = 1; r <= ret; r++) {
			dem += r;
		}
		for (int r = ret - 1; r >= 1; r--) {
			dem += r;
		}
		return dem;
	}
}
