
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Chklist {

	public static Boolean chklist(String[] arr) {
		if (arr.length == 0) {
			return false;
		}
		int index = 0;
		while (index < arr.length - 1) {
			if (!arr[index].equals(arr[index + 1])) {
				return false;
			}
			index++;
		}
		return true;
	}
}
