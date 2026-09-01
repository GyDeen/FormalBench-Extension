import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SetLeftMostUnsetBit {

	public static int setLeftMostUnsetBit(int n) {
		int pos, temp, count = 0;
		int ret = n & (n + 1);
		if ((ret) == 0) {
			return n;
		}
		pos = 0;
		temp = n;
		while (temp != 0) {
			int c = temp & 1;
			if ((c) == 0) {
				pos = count;
			}
			count++;
			temp = temp >> 1;
		}
		int result = 1 << pos;
		return n | (result);
	}
}
