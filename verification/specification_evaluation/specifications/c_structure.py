"""C-specific declaration checks for shared loop-structure matching."""
from __future__ import annotations

from verification.specification_evaluation.specifications.c_bindings import Function, normalize
from verification.specification_evaluation.specifications.loop_structure import match_loop_structure
from verification.specification_evaluation.manifest import InputError


def _validate_inert_declaration_gap(target: Function, next_target: int,
                                    gap: list[str]) -> bool:
    """Allow only preserved constant int32_t declarations between C loops."""
    gap = normalize(gap)
    declarations = []
    while gap:
        if (len(gap) < 5 or gap[0] != "int32_t" or gap[2] != "="
                or not gap[3].isdigit() or gap[4] != ";"):
            return False
        declarations.append(gap[:5])
        gap = gap[5:]
    for declaration in declarations:
        candidates = [item for item in target.visible(next_target).values()
                      if item.type == declaration[0] and item.name == declaration[1]
                      and normalize(item.initializer) == declaration[3:4]]
        if len(candidates) != 1:
            return False
    return True


def loop_correspondence(before: list[str], after: list[str], original_span: tuple,
                        target_span: tuple) -> tuple[dict[int, int], dict[int, int]]:
    """Match C loops while checking that moved local declarations survive."""
    target = Function(after, target_span)

    def validate_gap(_old_loop: int, _following: int, next_target: int,
                     gap: list[str]) -> bool:
        """Check one deleted loop's intervening C declarations against target scope."""
        return _validate_inert_declaration_gap(target, next_target, gap)

    return match_loop_structure(before, after, original_span, target_span,
                                normalize_header=normalize,
                                validate_deleted_gap=validate_gap)
