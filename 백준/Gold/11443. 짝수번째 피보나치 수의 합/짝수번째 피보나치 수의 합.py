import sys
N = int(sys.stdin.readline())
x = [[1,1],[1,0]]
def mult(a,b): # 행렬의 곱을 구하자
    A = [[0,0],[0,0]] # 2차원 행렬
    for i in range(2): 
        for j in range(2):
            for k in range(2):
                A[i][j] += a[i][k] * b[k][j] % 1000000007 # a, b 행렬의 곱 이고 중간에 나눠도 결과는 동일
    return A  # 나누지 않고 반환하면 값이 너무 커서 처리시간 증가 

def f(x, n): #행렬을 n번 곱했을 때의 피보나치 수열 구하기
    if n == 1: 
        return x 
    else : 
        matrix = f(x,n//2) # 행렬이 n이 1이 될 때까지 재귀 반복 
        if n % 2 == 0: # 짝수면
            return mult(matrix, matrix) # 행렬끼리 곱해주기
        else: # 홀수면
            return mult(mult(matrix,matrix), x) # 행렬 곱한거에 한번 더 곱해주기

result = f(x,N)
if N % 2 == 0 :
    print((result[0][0]-1)% 1000000007) 
else :
    print((result[0][1]-1)% 1000000007)
