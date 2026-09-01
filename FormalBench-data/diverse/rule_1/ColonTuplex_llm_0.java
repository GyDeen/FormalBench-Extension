
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ColonTuplex {

	public static Object[] colonTuplex(Object[] object, int num, int k) {
		Object[] ans = new Object[4];
		ans[0] = object[0];
		ans[1] = object[1];

		Object[] output = new Object[1];
		output[0] = k;
		ans[2] = output;

		ans[3] = object[3];

		return ans;
	}
}
