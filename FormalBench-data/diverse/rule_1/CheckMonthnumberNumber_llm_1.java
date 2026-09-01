import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnumberNumber {

	public static Boolean checkMonthnumberNumber(int number) {
		if (number == 4 || number == 6 || number == 9
				|| number == 11) {
			return true;
		}
		return false;
	}
}
