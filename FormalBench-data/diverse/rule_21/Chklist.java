
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Chklist {

	public static Boolean chklist(String[] arr) {
		if (0 == arr.length) {
			return false;
		}
		for (int i = 0; i < arr.length - 1; i++) {
			if (!arr[i].equals(arr[i + 1])) {
				return false;
			}
		}
		return true;
	}
}
