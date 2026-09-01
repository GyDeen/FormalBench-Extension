import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetFirstSetBitPos {

	public static int getFirstSetBitPos(int n) {
		int i = 1;
		while ((n & 1) == 0) {
			i++;
			n >>= 1;
		}
		return i;
	}
}
