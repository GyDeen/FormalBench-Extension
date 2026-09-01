
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultipleToSingle {

	public static int multipleToSingle(int[] args) {
		StringBuilder s = new StringBuilder();
		for (int i : args) {
			s.append(i);
		}
		return Integer.parseInt(s.toString());
	}
}
