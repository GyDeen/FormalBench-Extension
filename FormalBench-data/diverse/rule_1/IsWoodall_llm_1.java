import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsWoodall {

	public static Boolean isWoodall(int depth) {
		if (depth % 2 == 0) {
			return false;
		}

		if (depth == 1) {
			return true;
		}

		depth++;

		int end = 0;
		while (depth % 2 == 0) {

			depth = depth / 2;

			end++;

			if (end == depth) {
				return true;
			}
		}

		return false;
	}
}
