import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumNums {

	public static int sumNums(int num, int z, int c, int p) {
		int ret = num + z;
		if (ret >= c && ret <= p) {
			return 20;
		} else {
			return ret;
		}
	}
}
