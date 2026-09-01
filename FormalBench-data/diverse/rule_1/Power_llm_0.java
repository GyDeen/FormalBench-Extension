import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Power {

	public static int power(int number, int c) {
		if (c == 0) {
			return 1;
		}
		return number * power(number, c - 1);
	}
}
