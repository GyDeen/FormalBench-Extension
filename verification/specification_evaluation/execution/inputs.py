"""Shared, deterministic EvoSuite-plus-bounded execution input procedure."""
import itertools
import json
import random
MAXINT=2**31-1
def int32(x): return (x+2**31)%2**32-2**31

def candidates(p, parameter_list):
    """Deterministic bounded search across the complete program population."""
    rng = random.Random(726)
    params = parameter_list
    arrays = [[], [0], [-1], [1], [2], [1, 0], [0, 1], [2, 1], [-1, 0, 1], [3, 1, 2], [1, 1, 1], [4, 3, 2, 1], [-2, -1], [-2 ** 31, MAXINT]]
    arrays += [list(v) for n in range(2, 5) for v in itertools.product((-1, 0, 1), repeat=n)]
    arrays += [[rng.choice((-3, -1, 0, 1, 2, 4, MAXINT, -2 ** 31)) for _ in range(rng.randrange(1, 8))] for _ in range(60)]
    scalar_names = [name for kind, name in params if kind == 'int32_t']
    array_names = [name for kind, name in params if kind.startswith('J')]
    rows = []
    if not array_names:
        small = (-2, -1, 0, 1, 2, 3, 4, 7)
        rows += [dict(zip(scalar_names, v)) for v in itertools.product(small, repeat=len(params))]
        for _ in range(120):
            rows.append({n: rng.choice(small + (-2 ** 31, MAXINT, 16, 31, 32, 63)) for n in scalar_names})
        if p == 'OddBitSetNumber':
            rows += [{'n': int32(1 << b)} for b in range(32)]
        if p == 'NextPowerOf2':
            rows += [{'n': v} for v in (8, 9, 15, 16, 17, 2 ** 30)]
        if p == 'SqrtRoot':
            rows += [{'num': v} for v in (16, 17, 46340, 65536)]
    elif p in {'CountList', 'MaxDifference', 'MinCost'}:
        name = array_names[0]
        matrices = [[], [[]], [[0]], [[1, 2]], [[1, 2], [3, 4]], [[1, 0], [0, 1]], [[2, -1], [4, 3], [0, 0]]]
        matrices += [[[rng.choice((-2, -1, 0, 1, 2, 4)) for _ in range(c)] for _ in range(r)] for r in range(1, 5) for c in range(1, 5) for _ in range(5)]
        if p == 'CountList':
            matrices += [[[0] * n for n in ns] for count in range(5) for ns in itertools.product((0, 1, 2), repeat=count)]
        for a in matrices:
            if p == 'MinCost':
                for m, row in enumerate(a):
                    for n in range(len(row)):
                        rows.append({name: a, 'm': m, 'n': n})
            else:
                rows.append({name: a})
    elif p == 'SumList':
        rows += [{'arr1': a, 'arr2': b} for a in arrays[:14] for b in arrays[:14]]
        rows += [{'arr1': a, 'arr2': arrays[rng.randrange(len(arrays))]} for a in arrays]
    else:
        name = array_names[0]
        for a in arrays:
            if not scalar_names:
                rows.append({name: a})
            elif p == 'LeftInsertion':
                rows += [{name: a, 'x': v} for v in (-2, -1, 0, 1, 2, 3, 4)]
            elif p == 'SumRangeList':
                rows += [{name: a, 'm': m, 'n': n} for m in range(-1, len(a) + 1) for n in range(-1, len(a) + 1)]
            elif p == 'MinCoins':
                rows += [{name: a, 'm': m, 'v': v} for m in range(min(len(a), 3) + 1) for v in (-1, 0, 1, 2, 3, 4, 7)]
            else:
                nname = scalar_names[0]
                rows += [{name: a, nname: v} for v in sorted({-1, 0, 1, len(a) - 1, len(a), len(a) + 1})]
        if p in {'MoveFirst', 'MaxSubArraySum', 'SumOfSubarrayProd', 'FindPeak', 'MinJumps', 'MinCoins', 'SumRangeList'}:
            extra = {'MaxSubArraySum': {'size': 0}, 'SumOfSubarrayProd': {'n': 0}, 'FindPeak': {'n': 0}, 'MinJumps': {'n': 1}, 'MinCoins': {'m': 0, 'v': 0}, 'SumRangeList': {'m': 1, 'n': 0}}.get(p, {})
            rows.insert(0, {name: None, **extra})
    seen, result = (set(), [])
    for row in rows:
        key = json.dumps(row, sort_keys=True)
        if key not in seen:
            seen.add(key)
            result.append(row)
    return result
