import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ProdSquare {

	public static Boolean prodSquare(int p) {
		for (int j = 1; j <= p; j = j * 2) {
			if (j * j == p) {
				return true;
			}
		}
		return false;
	}
}
