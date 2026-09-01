
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ColonTuplex {

	public static Object[] colonTuplex(Object[] args, int num, int k) {
		Object[] ans = new Object[4];
		ans[0] = args[0];
		ans[1] = args[1];

		Object[] output = new Object[1];
		output[0] = k;
		ans[2] = output;

		ans[3] = args[3];

		return ans;
	}
}
