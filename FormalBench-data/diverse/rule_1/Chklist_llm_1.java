
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Chklist {

	public static Boolean chklist(String[] string) {
		if (string.length == 0) {
			return false;
		}
		for (int index = 0; index < string.length - 1; index++) {
			if (!string[index].equals(string[index + 1])) {
				return false;
			}
		}
		return true;
	}
}
