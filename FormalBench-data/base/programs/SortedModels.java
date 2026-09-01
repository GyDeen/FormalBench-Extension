
import java.io.*;
import java.util.*;
import java.math.*;

class SortedModels {
    
    public static HashMap<String, Object>[] sortedModels(HashMap<String, Object>[] models) {
        Arrays.sort(models, (m1, m2) -> {
            return Integer.compare((int) m2.get("model"), (int) m1.get("model"));
        });
        return models;
    }
}

