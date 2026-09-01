import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsCoprime {

	public static Boolean isCoprime(int version, int z) {
		if (version == 1 || z == 1) {
			return false;
		}
		if (version == z) {
			return true;
		}
		if (version > z) {
			int n = version;
			version = z;
			z = n;
		}
		for (int j = 2; j <= z / 2; j++) {
			if (version % j == 0 && z % j == 0) {
				return false;
			}
		}
		return true;
	}
}
