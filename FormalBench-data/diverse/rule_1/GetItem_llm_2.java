
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetItem {

	public static String getItem(Object[] args, int n) {
		if (n < 0) {
			n = args.length + n;
		}
		return (String) args[n];
	}
}
