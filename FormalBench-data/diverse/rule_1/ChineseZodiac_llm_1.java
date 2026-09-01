import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ChineseZodiac {

	public static String chineseZodiac(int code) {
		String[] result = { "Monkey", "Rooster", "Dog", "Pig", "Rat", "Ox", "Tiger", "Rabbit", "Dragon",
				"Snake", "Horse" };
		return result[code % 12];
	}
}
