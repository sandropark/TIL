public class M_1_1 {
    static void func1(A m){
        m.a *= 10;
    }
    
    static void func2(A m){
        m.a += m.b;
    }
       
    public static void main(String args[]){
       A m = new A();
       
       m.a = 100;
       func1(m);    // m.a=1000
       m.b = m.a;   // m.b=1000
       func2(m);    // m.a=2000
       
       System.out.printf("%d", m.a); // 2000
    }

    static class A {
        int a;
        int b;
    }
}