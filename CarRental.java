package car_rental_system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRental {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRental(){
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car){
        cars.add(car);
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days){
        if (car.isAvailabe()){
            car.rent();
            rentals.add(new Rental(car,customer, days));
        }else {
            System.out.println("Car is not available for rent.");
        }
    }

    public void returnCar(Car car){
        car.returnCar();
        Rental rentalToRemove = null;
        for (Rental rental:rentals){
            if (rental.getCar() == car){
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null){
            rentals.remove(rentalToRemove);
            System.out.println("Car successfully returned.");
        }else {
            System.out.println("car was not rented.");
        }
    }

    public void menu(){
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("===== Car Rental System =====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.println("Enter your choice");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1){
                System.out.println("\n== Rent a Car ==\n");
                System.out.println("Enter your name: ");
                String customerName = scanner.nextLine();

                System.out.println("\nAvailable Cars");
                for (Car car : cars){
                    if (car.isAvailabe()){
                        System.out.println(car.getCarId() + "." + car.getBrand() + "." + car.getModel());
                    }
                }
                System.out.println("\nEnter the car Id you want to rent: ");
                String carId = scanner.nextLine();

                System.out.println("Enter the number of days for rental: ");
                int rentalDays = scanner.nextInt();
                scanner.nextLine();

                Customer newCustomer = new Customer("Jesus" + (customers.size() + 1), customerName);
                addCustomer(newCustomer);

                Car selectedCar = null;
                for (Car car : cars){
                    if (car.getCarId().equals(carId) && car.isAvailabe()){
                        selectedCar = car;
                        break;
                    }
                }

                if (selectedCar!= null){
                    double totalPrice = selectedCar.calculatePrice(rentalDays);
                    System.out.println("\n== Rental Information ==\n");
                    System.out.println("Customer ID: " + newCustomer.getCustomerId());
                    System.out.println("Customer Name: " + newCustomer.getName());
                    System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("Rental Days: " + rentalDays);
                    System.out.printf("Total Price: $%.2f%n", totalPrice);

                    System.out.print("\nConfirm rental (Y/N): ");
                    String confirm = scanner.nextLine();

                    if(confirm.equalsIgnoreCase("Y")){
                        rentCar(selectedCar, newCustomer, rentalDays);
                        System.out.println("\nCar rented successfully.");
                    }else {
                        System.out.println("\nRental canceled");
                    }
                }else {
                    System.out.println("\nInvalid car selection or car not available for rent.");
                }
            } else if (choice == 2) {
                System.out.println("\n== Return a Car ==\n");
                System.out.print("Enter the car ID your want to return: ");
                String carId = scanner.nextLine();

                Car carToReturn = null;
                for (Car car : cars){
                    if (car.getCarId().equals(carId) && !car.isAvailabe()){
                        carToReturn = car;
                        break;
                    }
                }
                if (carToReturn !=null){
                    Customer customer = null;
                    for (Rental rental : rentals){
                        if (rental.getCar() == carToReturn){
                            customer = rental.getCustomer();
                            break;
                        }
                    }
                    
                    if (customer !=null){
                        returnCar(carToReturn);
                        System.out.println("Car returned successfully by " + customer.getName());
                    }else {
                        System.out.println("Car was not rented or car information is missing.");
                    }
                }else {
                    System.out.println("Invalid car ID or car is not rented.");
                }
            } else if (choice == 3) {
                break;
            }else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
        System.out.println("\n Thank you for using Car Rental System!");
    }
}

