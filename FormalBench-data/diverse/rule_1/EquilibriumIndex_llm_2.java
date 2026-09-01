
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EquilibriumIndex {

	public static int equilibriumIndex(int[] indices) {
		int result = 0;
		int max = 0;

		for (int index : indices) {
			result += index;
		}

		for (int left = 0; left < indices.length; left++) {
			result -= indices[left];
			if (max == result) {
				return left;
			}
			max += indices[left];
		}
		return -1;
	}
}
