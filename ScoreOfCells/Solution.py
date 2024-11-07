def find_cells_with_score_k(n, m, table, k):
    dp = [[0] * m for _ in range(n)]
    dp[0][0] = 1
    for i in range(n):
        for j in range(m):
            if i > 0 and table[i][j] >= table[i - 1][j]:
                dp[i][j] += dp[i - 1][j]
            if j > 0 and table[i][j] >= table[i][j - 1]:
                dp[i][j] += dp[i][j - 1]
    result = []
    for i in range(n):
        for j in range(m):
            if dp[i][j] == k:
                result.append((i, j))
    return result if result else "NO"
n, m = 4, 3
table = [
    [6, 4, 5],
    [8, 5, 3],
    [9, 7, 2],
    [1, 9, 10]
]
k = 1
cells = find_cells_with_score_k(n, m, table, k)
if cells != "NO":
    for cell in cells:
        print(cell[0], cell[1])
else:
    print("NO")
n, m = 4, 3
table = [
    [6, 4, 5],
    [8, 5, 3],
    [9, 7, 2],
    [1, 9, 10]
]
k = 1
cells = find_cells_with_score_k(n, m, table, k)
if cells != "NO":
    for cell in cells:
        print(cell[0], cell[1])
else:
    print("NO")