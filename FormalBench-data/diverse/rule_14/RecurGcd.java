import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RecurGcd {

	public static int recurGcd(int a, int b) {
		if (!(a == 0))
			;
		else {
			return b;
		}
		if (!(b == 0))
			;
		else {
			return a;
		}
		if (!(a == b))
			;
		else {
			return a;
		}
		return recurGcd(b, a % b);
	}
}
