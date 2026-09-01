
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddLists {

	public static int[] addLists(int[] list, int[] output) {
		int[] add = new int[list.length + output.length];

		System.arraycopy(output, 0, add, 0, output.length);

		System.arraycopy(list, 0, add, output.length,
				list.length);

		return add;
	}
}
