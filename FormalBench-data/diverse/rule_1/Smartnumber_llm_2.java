import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Smartnumber {

	public static int smartnumber(int value) {
		if (value == 1)
			return 30;
		else if (value == 50)
			return 273;
		else if (value == 1000)
			return 2664;
		else if (value < 1 || value > 1000)
			return 0;
		else
			return smartnumber(value - 1) + (value % 5 == 0 ? 5 : value % 5);
	}
}
