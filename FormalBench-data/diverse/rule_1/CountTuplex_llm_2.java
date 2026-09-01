
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountTuplex {

	public static int countTuplex(int[] tu, int array) {
		int ret = 0;
		for (int index = 0; index < tu.length; index++) {
			if (tu[index] == array) {
				ret++;
			}
		}
		return ret;
	}
}
