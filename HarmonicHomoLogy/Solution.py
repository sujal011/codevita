from collections import defaultdict, deque

# Step 1: Parse the hierarchy and build a tree
def build_hierarchy_tree(n, hierarchy_data):
    hierarchy = defaultdict(list)
    for entry in hierarchy_data:
        parent, children = entry.split(" : ")
        hierarchy[parent] = children.split()
    return hierarchy

# Step 2: Calculate levels of each tune using BFS/DFS traversal
def assign_levels(hierarchy, root):
    levels = {}
    queue = deque([(root, 0)])
    while queue:
        node, level = queue.popleft()
        levels[node] = level
        for child in hierarchy[node]:
            queue.append((child, level + 1))
    return levels

# Step 3: Compute the concordance score using DP
def max_concordance_score(melody1, melody2, A, B, C, levels):
    m, n = len(melody1), len(melody2)
    dp = [[float('-inf')] * (n + 1) for _ in range(m + 1)]
    dp[0][0] = 0

    for i in range(m + 1):
        for j in range(n + 1):
            if i < m:  # Remove from melody1
                dp[i + 1][j] = max(dp[i + 1][j], dp[i][j] - C)
            if j < n:  # Remove from melody2
                dp[i][j + 1] = max(dp[i][j + 1], dp[i][j] - C)
            if i < m and j < n:  # Compare tunes
                if melody1[i] == melody2[j] or levels[melody1[i]] == levels[melody2[j]]:
                    dp[i + 1][j + 1] = max(dp[i + 1][j + 1], dp[i][j] + A)
                else:
                    dp[i + 1][j + 1] = max(dp[i + 1][j + 1], dp[i][j] - B)
    
    return dp[m][n]

# Main function to handle input and output
def harmonic_homology():
    # Read number of parent nodes
    n = int(input().strip())
    
    # Read hierarchy data
    hierarchy_data = [input().strip() for _ in range(n)]
    hierarchy = build_hierarchy_tree(n, hierarchy_data)
    
    # Identify the root node
    root = next(iter(hierarchy))  # The first parent is always the root
    
    # Assign levels to each tune
    levels = assign_levels(hierarchy, root)
    
    # Read the melodies
    melody1_str = input().strip()
    melody2_str = input().strip()
    melody1 = melody1_str.split('-')
    melody2 = melody2_str.split('-')
    
    # Read values for A, B, and C
    A, B, C = map(int, input().strip().split())
    
    # Calculate the maximum concordance score
    result = max_concordance_score(melody1, melody2, A, B, C, levels)
    
    # Output the result
    print(result)

# Call the function
harmonic_homology()
