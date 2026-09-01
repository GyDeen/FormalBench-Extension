import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SetLeftMostUnsetBit {

	public static int setLeftMostUnsetBit(int n) {
		if ((n & (n + 1)) == 0) {
			return n;
		}
		int pos, temp, count = 0;
		temp = n;
		pos = 0;
		while (temp != 0) {
			if ((temp & 1) == 0) {
				pos = count;
			}
			temp = temp >> 1;
			count++;
		}
		return n | (1 << pos);
	}
}
