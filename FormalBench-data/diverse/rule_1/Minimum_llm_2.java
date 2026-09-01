import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Minimum {

	public static int minimum(int limit, int c) {
		if (limit < c) {
			return limit;
		} else {
			return c;
		}
	}
}
