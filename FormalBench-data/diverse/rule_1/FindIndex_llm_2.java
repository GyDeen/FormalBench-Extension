import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindIndex {

	public static int findIndex(int search) {
		int i = (int) Math.round(Math.sqrt(2 * Math.pow(10, (search - 1))));
		return i;
	}
}
