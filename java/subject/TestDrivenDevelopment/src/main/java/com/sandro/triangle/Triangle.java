package com.sandro.triangle;

class Triangle {
    private final int a;
    private final int b;
    private final int c;

    public Triangle(int a, int b, int c) {
        validate(a, b, c);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int code() {
        if (isEquilateral())    // 정삼각형
            return 1;
        else if (isScalene())   // 부등변삼각형
            return 3;
        else
            return 2;
    }

    private void validate(int a, int b, int c) {
        if (isSmallerThan1(a, b, c) || isNotTriangle(a, b, c))
            throw new IllegalArgumentException();
    }

    private boolean isNotTriangle(int a, int b, int c) {
        return a + b <= c || a + c <= b || b + c <= a;
    }

    private boolean isSmallerThan1(int a, int b, int c) {
        return a < 1 || b < 1 || c < 1;
    }

    private boolean isScalene() {
        return a != b && a != c && b != c;
    }

    private boolean isEquilateral() {
        return a == b && a == c;
    }
}
