import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddDays {

	public static int oddDays(int number) {
		int even = number / 100;
		int k = number / 400;
		int h = number / 4;
		int odd = number - h;
		if (even != 0) {
			odd += even;
			h -= even;
		}
		if (k != 0) {
			odd -= k;
			h += k;
		}
		int days = odd + h * 2;
		return days % 7;
	}
}
