
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DivEvenOdd {

	public static int divEvenOdd(int[] arr) {
		int div = -1;
		int last = -1;

		for (int i : arr) {
			if (div == -1 && i % 2 == 0) {
				div = i;
			}
			if (last == -1 && i % 2 != 0) {
				last = i;
			}
			if (div != -1 && last != -1) {
				break;
			}
		}

		if (last == 0)
			return 0;
		return div / last;
	}
}
