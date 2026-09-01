import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetFirstSetBitPos {

	public static int getFirstSetBitPos(int num) {
		int ret = 1;
		while ((num & 1) == 0) {
			num >>= 1;
			ret++;
		}
		return ret;
	}
}
