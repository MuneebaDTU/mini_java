Assignment 5 – Mini Java

Overview

In this assignment we extended the Mini Java interpreter by implementing additional
operator semantics, print statements, and while loops. We also added tests to increase
coverage and verify correct behavior of the interpreter.

# Part 5a – Operators and Print Statements

The following operators were implemented in both the type visitor (ProgramTypeVisitor)
and the executor visitor (ProgramExecutorVisitor):

## Unary operators
`
•	+ (PLUS1) for INT and FLOAT
•	- (MINUS1) for INT and FLOAT
`
## Binary operators
`
•	- (MINUS2)
•	* (MULT)
•	/ (DIV)
•	% (MOD)
`
These operators were added to:
•	operatorTypes in ProgramTypeVisitor
•	operatorFunctions in ProgramExecutorVisitor

Each operator checks that both operands have the same type 
and that the type is supported by the operator.

Then the PrintStatement was implemented in ProgramExecutorVisitor.

The implementation evaluates the expression retrieves its current value  
and prints the prefix and the value

 Example output:

x = 7

# **Part 5b – While Loops**

Support for while loops was implemented in both visitors.

Type Checking

`In ProgramTypeVisitor.visit(WhileLoop):
•	The loop expression is evaluated.
•	The expression must have type INT.
•	If the type is not INT, a problem is added to the problem list.`

Execution

`In ProgramExecutorVisitor.visit(WhileLoop):
•   The loop expression is evaluated. 
•   The loop executes while the expression value is greater than or equal to 0.
•   After each iteration, the expression is evaluated again.
`
⸻

# Part 5c – Tests and Coverage

Additional tests were added in a new file "ProgramVisitorTest":


The tests cover:
`
•	Unary operators
•	Binary operators
•	Print statements
•	Assignments
•	Variable declarations
•	Type mismatches
•	While loop execution
•	Nested while loops
•	Incorrect typing scenarios
`

An additional loop test provided by the instructors (test2LoopProgram) was added 
to ensure that while loop execution behaves correctly.


# Coverage Results

Using IntelliJ test coverage tools:

Class	Line Coverage
ProgramExecutorVisitor	97%
ProgramTypeVisitor	91%

Both exceed the required 90% coverage for Assignment 5.
