import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindDivisor {

	public static int findDivisor(int num, int z) {
		if (z < 0) {
			z = -z;
		}
		int result = 0;
		for (int index = 1; index <= z; index++) {
			if (num % index == 0) {
				result = result + 1;
			}
		}
		return result;
	}
}
