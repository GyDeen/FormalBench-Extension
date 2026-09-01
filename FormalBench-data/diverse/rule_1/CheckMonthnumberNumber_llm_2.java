import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnumberNumber {

	public static Boolean checkMonthnumberNumber(int num) {
		if (num == 4 || num == 6 || num == 9
				|| num == 11) {
			return true;
		}
		return false;
	}
}
