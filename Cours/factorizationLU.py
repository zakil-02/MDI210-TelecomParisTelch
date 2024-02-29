import numpy as np
def factorisationLU(A):
    A_ex = A
    L=np.eye(len(A), dtype=float)
     
    for k in range(len(A)-1):
       if A[k][k]==0:
           print("Pivot nul! use PLU...")
           break
       else:
           for i in range(k+1, len(A)):
                L[i][k]=A[i][k]/A[k][k]
                A[i]-=(A[i][k]/A[k][k])*A[k]

    U=A
    print("Décomposition LU de : A=\n", A_ex)
    print("-------------------------------")
    print("L=\n", L)
    print("U=\n", U)



A=np.array([[1,2,2,2],
            [1,1,1,1],
            [1,3,2,0],
            [1,1,1,2]], dtype=float)
factorisationLU(A)
    
