(* Compatibility for Frama-C 33.0, Logic_utils.expr_to_term, float UnOp branch.
   That branch calls expr_to_term ~coerce:true and passes a real argument to
   \neg_double/\neg_float, although these builtins require a floating value.
   Generate the same RTE checks as WP, then remove ONLY that implicit coercion.
   No executable AST nodes, user predicates, or proof obligations are removed. *)
open Cil_types

module Self = Plugin.Register
    (struct
      let name = "Paired C float compatibility"
      let shortname = "paired-c"
      let help = "Repair Frama-C 33.0 generated float negation annotations"
    end)

module Prepare = Self.False
    (struct
      let option_name = "-paired-c-prepare"
      let help = "Generate WP-compatible RTE checks and repair implicit float casts"
    end)

class repair = object
  inherit Visitor.frama_c_inplace
  val mutable repairs = 0
  method count = repairs
  method! vterm t =
    match t.term_node with
    | Tapp (f, labels, [{term_node = TCast (true, Lreal, inner); _}])
      when List.mem f.l_var_info.lv_name
          ["\\neg_float"; "\\neg_double"; "\\neg_float32"; "\\neg_float64"] ->
      (match f.l_profile with
       | [parameter] when Ast_types.is_logic_float inner.term_type &&
           Cil_datatype.Logic_type.equal parameter.lv_type inner.term_type ->
         repairs <- repairs + 1;
         Cil.ChangeDoChildrenPost
           ({t with term_node = Tapp (f, labels, [inner])}, Fun.id)
       | _ -> Self.abort "Unexpected floating negation argument signature")
    | _ -> Cil.DoChildren
end

let () = Boot.Main.extend (fun () ->
  if Prepare.get () then begin
    Prepare.set false;
    Ast.compute ();
    (* These are exactly the two exclusions in WP 33.0's WpRTE.generate. *)
    let flags = { (RteGen.Flags.default ()) with
                  pointer_alignment = false; pointer_call = false } in
    Globals.Functions.iter (fun kf ->
      if Kernel_function.is_definition kf then RteGen.Visit.annotate ~flags kf);
    let visitor = new repair in
    Visitor.visitFramacFileSameGlobals (visitor :> Visitor.frama_c_visitor) (Ast.get ());
    Self.feedback "Repaired %d generated floating negation coercions" visitor#count
  end)
