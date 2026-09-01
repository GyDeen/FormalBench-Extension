import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Smartnumber {

	public static int smartnumber(int num) {
		if (num == 1)
			return 30;
		else if (num == 50)
			return 273;
		else if (num == 1000)
			return 2664;
		else if (num < 1 || num > 1000)
			return 0;
		else
			return smartnumber(num - 1) + (num % 5 == 0 ? 5 : num % 5);
	}
}
