import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SetLeftMostUnsetBit {

	public static int setLeftMostUnsetBit(int left) {
		int ret, offset, i = 0;
		if ((left & (left + 1)) == 0) {
			return left;
		}
		ret = 0;
		offset = left;
		while (offset != 0) {
			if ((offset & 1) == 0) {
				ret = i;
			}
			i++;
			offset = offset >> 1;
		}
		return left | (1 << ret);
	}
}
