
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Chklist {

	public static Boolean chklist(String[] arr) {
		if (arr.length == 0) {
			return false;
		}
		int index = arr.length - 1;
		for (int i = 0; i < index; i++) {
			if (!arr[i].equals(arr[i + 1])) {
				return false;
			}
		}
		return true;
	}
}
