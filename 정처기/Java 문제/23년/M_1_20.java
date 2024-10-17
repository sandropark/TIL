public class M_1_20 {
    public static void main(String[] args) {
        Child obj = new Child();
        System.out.println(obj.getX()); // 500
    }

    static class Parent {
        int x = 100;

        Parent() {
            this(500);
        }

        Parent(int x) {
            this.x = x;
        }

        int getX() { // 이 메서드는 override되지 않았기 때문에 부모의 필드의 값을 반환한다.
            return x;
        }
    }

    static class Child extends Parent {
        int x = 1000;

        Child() { // 자식의 생성자 호출이후 부모의 기본 생성자가 호출된다.
            this(5000);
        }

        Child(int x) {
            this.x = x;
        }
    }
}
