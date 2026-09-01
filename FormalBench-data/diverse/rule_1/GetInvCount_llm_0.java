
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetInvCount {

	public static int getInvCount(int[] args, int num) {
		int ret = 0;

		for (int index = 0; index < num; index++) {
			for (int c = index + 1; c < num; c++) {
				if (args[index] > args[c]) {
					ret++;
				}
			}
		}
		return ret;
	}
}
