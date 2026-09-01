
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleToSet {

	public static HashSet<String> tupleToSet(String[] args) {
		HashSet<String> empty = new HashSet<>();
		for (String string : args) {
			empty.add(string);
		}
		return empty;
	}
}
