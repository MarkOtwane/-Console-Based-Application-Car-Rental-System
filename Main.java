package car_rental_system;

public class Main {
    public static void main(String[] args) {
        CarRental carRental = new CarRental();

        Car car1 = new Car("c001", "Toyota", "Canry", 60.0);
        Car car2 = new Car("c005", "Toyota", "Honda", 70.0);
        carRental.addCar(car1);
        carRental.addCar(car2);


        carRental.menu();
    }
}
