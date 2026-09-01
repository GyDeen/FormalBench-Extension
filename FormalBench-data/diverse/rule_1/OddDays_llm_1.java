import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddDays {

	public static int oddDays(int number) {
		int odd = number / 100;
		int k = number / 400;
		int h = number / 4;
		int even = number - h;
		if (odd != 0) {
			even += odd;
			h -= odd;
		}
		if (k != 0) {
			even -= k;
			h += k;
		}
		int days = even + h * 2;
		return days % 7;
	}
}
