
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountX {

	public static int countX(int[] args, int array) {
		int ret = 0;
		for (int i : args) {
			if (i == array) {
				ret++;
			}
		}
		return ret;
	}
}
