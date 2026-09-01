import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumCommDiv {

	public static int numCommDiv(int num, int z) {
		int ret = 0;
		for (int index = 1; index <= num
				&& index <= z; index++) {
			if (num % index == 0 && z % index == 0) {
				ret++;
			}
		}
		return ret;
	}
}
