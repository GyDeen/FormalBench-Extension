
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsMajority {

	public static Boolean isMajority(int[] arr, int n, int x) {
		int count = 0;
		for (int i = 0; n > i; i++) {
			if (arr[i] == x) {
				count++;
			}
		}
		return n / 2 < count;
	}
}
