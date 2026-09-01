
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Chklist {

	public static Boolean chklist(String[] arr) {
		if (arr.length == 0) {
			return false;
		}
		int i = 0;
		while (i < arr.length - 1) {
			if (!arr[i].equals(arr[i + 1])) {
				return false;
			}
			i++;
		}
		return true;
	}
}
