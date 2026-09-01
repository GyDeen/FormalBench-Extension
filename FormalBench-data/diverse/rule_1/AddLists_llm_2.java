
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddLists {

	public static int[] addLists(int[] list, int[] add) {
		int[] result = new int[list.length + add.length];

		System.arraycopy(add, 0, result, 0, add.length);

		System.arraycopy(list, 0, result, add.length,
				list.length);

		return result;
	}
}
