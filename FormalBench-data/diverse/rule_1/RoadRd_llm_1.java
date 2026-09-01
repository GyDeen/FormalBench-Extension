import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RoadRd {

	public static String roadRd(String path) {
		return path.replace("Road", "Rd.").replace("road", "rd");
	}
}
