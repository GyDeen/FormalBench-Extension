import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SetLeftMostUnsetBit {

	public static int setLeftMostUnsetBit(int left) {
		int result, offset, i = 0;
		if ((left & (left + 1)) == 0) {
			return left;
		}
		result = 0;
		offset = left;
		while (offset != 0) {
			if ((offset & 1) == 0) {
				result = i;
			}
			i++;
			offset = offset >> 1;
		}
		return left | (1 << result);
	}
}
