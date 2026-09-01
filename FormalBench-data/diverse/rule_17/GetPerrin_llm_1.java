import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPerrin {

	public static int getPerrin(int n) {
		if (n == 0) {
			return 3;
		}
		if (n == 1) {
			return 0;
		}
		if (n == 2) {
			return 2;
		}
		int p = n - 2;
		int temp = n - 3;
		return getPerrin(p) + getPerrin(temp);
	}
}
