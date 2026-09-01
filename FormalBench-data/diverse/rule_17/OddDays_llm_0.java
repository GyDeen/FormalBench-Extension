import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddDays {

	public static int oddDays(int n) {
		int hund1 = n / 100;
		int hund4 = n / 400;
		int leap = n / 4;
		int ordd = n - leap;
		if (hund1 != 0) {
			ordd += hund1;
			leap -= hund1;
		}
		if (hund4 != 0) {
			ordd -= hund4;
			leap += hund4;
		}
		int or = leap * 2;
		int days = ordd + or;
		return days % 7;
	}
}
