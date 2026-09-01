import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumCommDiv {

	public static int numCommDiv(int n, int z) {
		int ret = 0;
		for (int index = 1; index <= n
				&& index <= z; index++) {
			if (n % index == 0 && z % index == 0) {
				ret++;
			}
		}
		return ret;
	}
}
