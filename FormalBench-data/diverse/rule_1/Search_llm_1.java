
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Search {

	public static int search(int[] args, int array) {
		int result = 0;
		while (result < array - 1) {
			if (args[result] == args[result + 1]) {
				result += 2;
			} else {
				return args[result];
			}
		}
		return args[array - 1];
	}
}
