import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPerrin {

	public static int getPerrin(int p) {
		if (p == 0) {
			return 3;
		}
		if (p == 1) {
			return 0;
		}
		if (p == 2) {
			return 2;
		}
		return getPerrin(p - 2) + getPerrin(p - 3);
	}
}
