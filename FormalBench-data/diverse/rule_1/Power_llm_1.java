import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Power {

	public static int power(int p, int c) {
		if (c == 0) {
			return 1;
		}
		return p * power(p, c - 1);
	}
}
