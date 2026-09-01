import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SetLeftMostUnsetBit {

	public static int setLeftMostUnsetBit(int left) {
		int i, offset, tmp = 0;
		if ((left & (left + 1)) == 0) {
			return left;
		}
		i = 0;
		offset = left;
		while (offset != 0) {
			if ((offset & 1) == 0) {
				i = tmp;
			}
			tmp++;
			offset = offset >> 1;
		}
		return left | (1 << i);
	}
}
