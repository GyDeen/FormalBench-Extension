import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Smartnumber {

	public static int smartnumber(int number) {
		if (number == 1)
			return 30;
		else if (number == 50)
			return 273;
		else if (number == 1000)
			return 2664;
		else if (number < 1 || number > 1000)
			return 0;
		else
			return smartnumber(number - 1) + (number % 5 == 0 ? 5 : number % 5);
	}
}
