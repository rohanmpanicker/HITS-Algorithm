# HITS-Algorithm

Compile instruction: javac hits0880.java

Execution instruction: java hits0880 <INITIAL_VALUE>

The INITIAL_VALUE helps us to set-up the initial values of iteration 0 as needed.

Argument INITIAL_VALUE sets the initial vector values. If it is 0 they are initialized to 0, if it is 1 they are initialized to 1. If it is -1 they are initialized to 1/N, where N is the number of web-pages (vertices of the graph). If it is -2 they are initialized to square root(1/N).

Argument filename describes the input (directed) graph and it has the following form. The first line contains two numbers: the number of vertices followed by the number of edges which is also thenumber of remaining lines. PAY ATTENTION THAT NUMBER of VERTICES comes first.

Our graph has (directed) edges (0,2),(0,3),(1,0),(2,1). Vector values are printed to 7 decimal digits. If the graph has N GREATER than 10, then the values for iterations, initialvalue are automatically set to 0 and -1 respectively. In such a case the hub/authority/pageranks at the stopping iteration (i.e t) are ONLY shown, one per line. The graph below will be referred to as samplegraph.txt

4 4

0 2

0 3

1 0

2 1

The following invocations relate to samplegraph.txt, with a fixed number of iterations and the fixed error rate that determines how many iterations will run. Your code should compute for this graph the same rank values (intermediate and final). A sample of the output for the case of N >10 is shown (output truncated to first 4 lines of it).


OUTPUT:

Base : 0 :A/H[ 0]=0.3333333/0.3333333 A/H[ 1]=0.3333333/0.3333333 A/H[ 2]=0.3333333/0.3333333
Iter : 1 :A/H[ 0]=0.0000000/0.8320503 A/H[ 1]=0.4472136/0.5547002 A/H[ 2]=0.8944272/0.0000000

or for large graphs
Iter : 37

A/H[ 0]=0.0000000/0.0000002

A/H[ 1]=0.0000001/0.0000238

A/H[ 2]=0.0000002/1.0000000

A/H[ 3]=0.0000159/0.0000000
