
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SubjectMarks {
    
    public static Object[][] subjectMarks(Object[][] subjectMarks) {
        Arrays.sort(subjectMarks, (a, b) -> {
            int first = (int)a[1];
            int second = (int)b[1];
            return Integer.compare(first, second);
        });
        return subjectMarks;
    }
}

