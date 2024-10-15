a = 100
result = 0

# 100 = 1100100

for i in range(1,3): # 1~2
   result = a >> i  # 그냥 덮어쓰기 하기 때문에 마지막 반복만 생각하면 된다. i=2
   result = result + 1

print(result) # 26

# 1100100 -> 11001+1 = 11010 = 26