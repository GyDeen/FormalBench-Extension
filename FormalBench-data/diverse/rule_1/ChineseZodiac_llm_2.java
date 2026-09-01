import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ChineseZodiac {

	public static String chineseZodiac(int n) {
		String[] chars = { "Monkey", "Rooster", "Dog", "Pig", "Rat", "Ox", "Tiger", "Rabbit", "Dragon",
				"Snake", "Horse" };
		return chars[n % 12];
	}
}
