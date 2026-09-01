
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EquilibriumIndex {

	public static int equilibriumIndex(int[] indices) {
		int index = 0;
		int max = 0;

		for (int n : indices) {
			index += n;
		}

		for (int left = 0; left < indices.length; left++) {
			index -= indices[left];
			if (max == index) {
				return left;
			}
			max += indices[left];
		}
		return -1;
	}
}
