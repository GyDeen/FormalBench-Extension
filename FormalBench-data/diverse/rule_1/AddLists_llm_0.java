
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddLists {

	public static int[] addLists(int[] list, int[] add) {
		int[] test = new int[list.length + add.length];

		System.arraycopy(add, 0, test, 0, add.length);

		System.arraycopy(list, 0, test, add.length,
				list.length);

		return test;
	}
}
