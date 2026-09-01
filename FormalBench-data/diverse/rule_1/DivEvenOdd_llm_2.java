
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DivEvenOdd {

	public static int divEvenOdd(int[] arr) {
		int div = -1;
		int last = -1;

		for (int index : arr) {
			if (div == -1 && index % 2 == 0) {
				div = index;
			}
			if (last == -1 && index % 2 != 0) {
				last = index;
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
