class Employee {

    String name;
    int salary;
    String address;
    public static final String UNKNOWN = "unknown";

    public Employee() {
        this.name = UNKNOWN;
        this.salary = 0;
        this.address = UNKNOWN;
    }

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
        this.address = UNKNOWN;
    }

    public Employee(String name, int salary, String address) {
        this.name = name;
        this.salary = salary;
        this.address = address;
    }
}