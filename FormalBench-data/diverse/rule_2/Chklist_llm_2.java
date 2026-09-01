
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Chklist {

	public static Boolean chklist(String[] arr) {
		if (arr.length == 0) {
			return false;
		}
		int n = 0;
		while (n < arr.length - 1) {
			if (!arr[n].equals(arr[n + 1])) {
				return false;
			}
			n++;
		}
		return true;
	}
}
