import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ProdSquare {

	public static Boolean prodSquare(int n) {
		for (int i = 1; n >= i; i = i * 2) {
			if (i * i == n) {
				return true;
			}
		}
		return false;
	}
}
