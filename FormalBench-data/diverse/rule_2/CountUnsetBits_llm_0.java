import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountUnsetBits {

	public static int countUnsetBits(int n) {
		int cnt = 0;
		int ret = 1;
		while (ret <= n) {
			int temp = ret;
			while (temp != 0) {
				if ((temp & 1) == 0) {
					cnt++;
				}
				temp = temp >> 1;
			}
			ret++;
		}
		return cnt;
	}
}
