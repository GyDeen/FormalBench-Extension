import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPerrin {

	public static int getPerrin(int n) {
		if (0 == n) {
			return 3;
		}
		if (1 == n) {
			return 0;
		}
		if (2 == n) {
			return 2;
		}
		return getPerrin(n - 2) + getPerrin(n - 3);
	}
}
