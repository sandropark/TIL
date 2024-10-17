public class M_1_18 {
    public static void main(String[] args) {
        Vehicle obj = new Car("Spark");
        System.out.print(obj.getName()); // Vehicle의 getName() 호출
        // Vehicle name:Spark
    }

    static abstract class Vehicle {
        String name; // Spark

        abstract public String getName(String val);

        public String getName() {
            return "Vehicle name:" + name;
        }
    }

    static class Car extends Vehicle {
        private String name; // Spark

        public Car(String val) {
            name = super.name = val;
        }

        public String getName(String val) {
            return "Car name : " + val;
        }

        public String getName(byte val[]) {
            return "Car name : " + val;
        }
    }
}
