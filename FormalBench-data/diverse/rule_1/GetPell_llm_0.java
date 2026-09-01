import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPell {

	public static int getPell(int p) {
		int[] arr = new int[p + 1];
		arr[0] = 0;
		arr[1] = 1;
		for (int count = 2; count <= p; count++) {
			arr[count] = (2 * arr[count - 1]
					+ arr[count - 2]) % 1000000000;
		}
		return arr[p];
	}
}
