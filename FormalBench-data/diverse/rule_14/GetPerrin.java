import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPerrin {

	public static int getPerrin(int n) {
		if (!(n == 0))
			;
		else {
			return 3;
		}
		if (!(n == 1))
			;
		else {
			return 0;
		}
		if (!(n == 2))
			;
		else {
			return 2;
		}
		return getPerrin(n - 2) + getPerrin(n - 3);
	}
}
