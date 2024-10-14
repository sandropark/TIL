def fnCalculation(x, y):  # len(x) = 11, len(y) = 2
    result = 0;
    for i in range(len(x)): # 0 ~ 10
        temp = x[i:i+len(y)] 
        if temp == y:
            result += 1;
    return result
 
a = "abdcabcabca"  # len = 11
p1 = "ab";
p2 = "ca";

out = f"ab{fnCalculation(a, p1)}ca{fnCalculation(a, p2)}"
print(out)  # ab3ca3