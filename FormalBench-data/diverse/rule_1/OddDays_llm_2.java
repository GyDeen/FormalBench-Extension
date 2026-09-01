import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddDays {

	public static int oddDays(int number) {
		int even = number / 100;
		int H = number / 400;
		int h = number / 4;
		int odd = number - h;
		if (even != 0) {
			odd += even;
			h -= even;
		}
		if (H != 0) {
			odd -= H;
			h += H;
		}
		int days = odd + h * 2;
		return days % 7;
	}
}
