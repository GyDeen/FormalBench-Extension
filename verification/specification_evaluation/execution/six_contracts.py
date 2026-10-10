"""Executable equivalents of six frozen C contracts."""
PARAMS = {'MaxOfTwo': ('x', 'y'), 'OddBitSetNumber': ('n',),
          'SumNums': ('x', 'y', 'm', 'n'), 'TestThreeEqual': ('x', 'y', 'z')}

def int32(value):
    return (value + 2**31) % 2**32 - 2**31

def oracle(program, inputs):
    """Executable equivalents of six frozen postconditions."""
    if program == 'MaxOfTwo':
        return max(inputs['x'], inputs['y'])
    if program == 'TestThreeEqual':
        x, y, z = (inputs[n] for n in PARAMS[program])
        return 3 if x == y == z else 2 if x == y or y == z or x == z else 0
    if program == 'SumNums':
        value = int32(inputs['x'] + inputs['y'])
        return 20 if inputs['m'] <= value <= inputs['n'] else value
    if program == 'OddBitSetNumber':
        n = inputs['n'] & 0xffffffff
        result = n
        for mask, shift in ((0xaaaaaaaa, 1), (0xcccccccc, 2), (0xf0f0f0f0, 4), (0xff00ff00, 8), (0xffff0000, 16)):
            result |= (n & mask) >> shift
        return int32(result)
    if program == 'CountList':
        return sum(bool(row) for row in inputs['inputArray'])
    if program == 'MaxSubArraySum':
        if inputs['size'] <= 0:
            return 1
        tail, best, start, length = 0, 0, 0, 1
        for k, value in enumerate(inputs['a'][:inputs['size']], 1):
            candidate = int32(tail + value)
            if best < candidate:
                length = k - start
            best = max(best, candidate)
            if candidate < 0:
                start = k
            tail = max(0, candidate)
        return length
    raise ValueError(program)
