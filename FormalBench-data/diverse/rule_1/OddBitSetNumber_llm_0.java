import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddBitSetNumber {

	public static int oddBitSetNumber(int num) {
		int num_ = num & 0xFFFFFFFF;
		num_ |= (num & 0xAAAAAAAA) >>> 1;
		num_ |= (num & 0xCCCCCCCC) >>> 2;
		num_ |= (num & 0xF0F0F0F0) >>> 4;
		num_ |= (num & 0xFF00FF00) >>> 8;
		num_ |= (num & 0xFFFF0000) >>> 16;
		return num_;
	}
}
