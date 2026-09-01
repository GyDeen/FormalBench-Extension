import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SetBitNumber {

	public static int setBitNumber(int value) {
		return (int) Math.pow(2, (int) (Math.log(value) / Math.log(2)));
	}
}
