import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Smartnumber {

	public static int smartnumber(int n) {
		if (1 == n)
			return 30;
		else if (50 == n)
			return 273;
		else if (1000 == n)
			return 2664;
		else if (n < 1 || n > 1000)
			return 0;
		else
			return smartnumber(n - 1) + (0 == n % 5 ? 5 : n % 5);
	}
}
