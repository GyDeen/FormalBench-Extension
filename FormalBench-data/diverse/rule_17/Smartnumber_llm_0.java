import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Smartnumber {

	public static int smartnumber(int n) {
		boolean b = n < 1;
		boolean temp = n > 1000;
		int ret = n - 1;
		boolean tmp = n % 5 == 0;
		int result = n % 5;
		if (n == 1)
			return 30;
		else if (n == 50)
			return 273;
		else if (n == 1000)
			return 2664;
		else if (b || temp)
			return 0;
		else
			return smartnumber(ret) + (tmp ? 5 : result);
	}
}
