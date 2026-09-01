
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultipleToSingle {

	public static int multipleToSingle(int[] values) {
		StringBuilder s = new StringBuilder();
		for (int i : values) {
			s.append(i);
		}
		return Integer.parseInt(s.toString());
	}
}
