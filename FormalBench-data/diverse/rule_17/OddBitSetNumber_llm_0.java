import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddBitSetNumber {

	public static int oddBitSetNumber(int n) {
		int n_ = n & 0xFFFFFFFF;
		int r = n & 0xAAAAAAAA;
		n_ |= (r) >>> 1;
		int tmp = n & 0xCCCCCCCC;
		n_ |= (tmp) >>> 2;
		int result = n & 0xF0F0F0F0;
		n_ |= (result) >>> 4;
		int p = n & 0xFF00FF00;
		n_ |= (p) >>> 8;
		int i = n & 0xFFFF0000;
		n_ |= (i) >>> 16;
		return n_;
	}
}
