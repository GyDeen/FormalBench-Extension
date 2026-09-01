
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EquilibriumIndex {

	public static int equilibriumIndex(int[] indices) {
		int result = 0;
		int max = 0;

		for (int n : indices) {
			result += n;
		}

		for (int index = 0; index < indices.length; index++) {
			result -= indices[index];
			if (max == result) {
				return index;
			}
			max += indices[index];
		}
		return -1;
	}
}
