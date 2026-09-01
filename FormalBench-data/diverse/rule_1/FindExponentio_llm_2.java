
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindExponentio {

	public static int[] findExponentio(int[] args, int[] test) {
		int[] find = new int[args.length];
		for (int index = 0; index < args.length; index++) {
			find[index] = (int) Math.pow(args[index],
					test[index]);
		}
		return find;
	}
}
