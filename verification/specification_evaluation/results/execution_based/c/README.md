# C runtime contract mutant detection

Primary: reproduced frozen return-value/content postcondition violations on admitted inputs. Safety and frame/other-clause failures are separate. Saved EvoSuite first calls precede bounded seed-726 inputs. O0/UBSan search and O1/ASan+UBSan replay use 0.5/2 second limits. Frozen contracts are matched by text; unchecked clauses and evaluation failures remain visible.

These are validated historical execution results promoted from the withdrawn C run, not a newly executed C experiment. All 79 primary witnesses are attributed to saved EvoSuite inputs. The configured input procedure includes bounded inputs.
