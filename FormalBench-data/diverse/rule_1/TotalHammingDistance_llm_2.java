import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TotalHammingDistance {

	public static int totalHammingDistance(int num) {
		int i = 0;
		while (num != 0) {
			i += (num & 1) ^ (num & 2) ^ (num & 4)
					^ (num & 8);
			num >>>= 1;
		}
		return i;
	}
}
