#include <stddef.h>
#include <stdint.h>
#include <stdlib.h>

typedef struct {
    int32_t *data;
    size_t length;
} Int32Array;

Int32Array countingSort(Int32Array myArray) {
    if (myArray.length == 0) {
        return (Int32Array){NULL, 0};
    }

    int32_t max = myArray.data[0];
    int32_t min = myArray.data[0];
    for (size_t i = 0; i < myArray.length; i++) {
        int32_t num = myArray.data[i];
        if (num > max) max = num;
        if (num < min) min = num;
    }

    int32_t range = max - min + 1;
    int32_t *countArray = calloc((size_t)range, sizeof(*countArray));
    int32_t *result = malloc(myArray.length * sizeof(*result));
    if (countArray == NULL || result == NULL) {
        free(countArray);
        free(result);
        return (Int32Array){NULL, 0};
    }

    for (size_t i = 0; i < myArray.length; i++) {
        countArray[myArray.data[i] - min]++;
    }

    size_t index = 0;
    for (int32_t i = 0; i < range; i++) {
        for (int32_t j = 0; j < countArray[i]; j++) {
            result[index++] = i + min;
        }
    }
    free(countArray);
    return (Int32Array){result, myArray.length};
}
