import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetFirstSetBitPos {

	public static int getFirstSetBitPos(int size) {
		int ret = 1;
		while ((size & 1) == 0) {
			size >>= 1;
			ret++;
		}
		return ret;
	}
}
