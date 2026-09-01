import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TotalHammingDistance {

	public static int totalHammingDistance(int size) {
		int i = 0;
		while (size != 0) {
			i += (size & 1) ^ (size & 2) ^ (size & 4)
					^ (size & 8);
			size >>>= 1;
		}
		return i;
	}
}
