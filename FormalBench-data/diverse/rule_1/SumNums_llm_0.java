import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumNums {

	public static int sumNums(int num, int b, int z, int p) {
		int ret = num + b;
		if (ret >= z && ret <= p) {
			return 20;
		} else {
			return ret;
		}
	}
}
