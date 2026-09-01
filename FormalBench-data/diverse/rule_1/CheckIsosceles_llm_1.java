import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckIsosceles {

	public static Boolean checkIsosceles(int id, int n, int c) {
		return (id != n && n != c
				&& id != c);
	}
}
