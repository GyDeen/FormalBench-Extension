import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SetBitNumber {

	public static int setBitNumber(int b) {
		return (int) Math.pow(2, (int) (Math.log(b) / Math.log(2)));
	}
}
