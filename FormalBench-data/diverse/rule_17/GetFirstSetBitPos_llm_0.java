import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetFirstSetBitPos {

	public static int getFirstSetBitPos(int n) {
		int i = 1;
		int j = n & 1;
		while ((j) == 0) {
			n >>= 1;
			i++;
		}
		return i;
	}
}
