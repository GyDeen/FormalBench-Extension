import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SetLeftMostUnsetBit {

	public static int setLeftMostUnsetBit(int n) {
		int pos, temp, count = 0;
		int result = n & (n + 1);
		if ((result) == 0) {
			return n;
		}
		pos = 0;
		temp = n;
		while (temp != 0) {
			int tmp = temp & 1;
			if ((tmp) == 0) {
				pos = count;
			}
			count++;
			temp = temp >> 1;
		}
		int ret = 1 << pos;
		return n | (ret);
	}
}
