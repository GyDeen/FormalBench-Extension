import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddBitSetNumber {

	public static int oddBitSetNumber(int number) {
		int number_ = number & 0xFFFFFFFF;
		number_ |= (number & 0xAAAAAAAA) >>> 1;
		number_ |= (number & 0xCCCCCCCC) >>> 2;
		number_ |= (number & 0xF0F0F0F0) >>> 4;
		number_ |= (number & 0xFF00FF00) >>> 8;
		number_ |= (number & 0xFFFF0000) >>> 16;
		return number_;
	}
}
