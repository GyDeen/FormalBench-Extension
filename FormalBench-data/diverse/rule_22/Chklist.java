
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Chklist {

	public static Boolean chklist(String[] arr) {
		if (arr.length == 0) {
			return false;
		}
		for (int i = 0; i < arr.length - 1; i++) {
			if (!arr[i + 1].equals(arr[i])) {
				return false;
			}
		}
		return true;
	}
}
