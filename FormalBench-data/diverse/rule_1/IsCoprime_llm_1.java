import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsCoprime {

	public static Boolean isCoprime(int code, int z) {
		if (code == 1 || z == 1) {
			return false;
		}
		if (code == z) {
			return true;
		}
		if (code > z) {
			int n = code;
			code = z;
			z = n;
		}
		for (int j = 2; j <= z / 2; j++) {
			if (code % j == 0 && z % j == 0) {
				return false;
			}
		}
		return true;
	}
}
