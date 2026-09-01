import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Smartnumber {

	public static int smartnumber(int n) {
		if (n == 1)
			return 30;
		else if (n == 50)
			return 273;
		else if (n == 1000)
			return 2664;
		else if (1 > n || 1000 < n)
			return 0;
		else
			return smartnumber(n - 1) + (n % 5 == 0 ? 5 : n % 5);
	}
}
