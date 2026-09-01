
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleToSet {

	public static HashSet<String> tupleToSet(String[] tu) {
		HashSet<String> empty = new HashSet<>();
		for (String string : tu) {
			empty.add(string);
		}
		return empty;
	}
}
