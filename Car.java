package car_rental_system;

public class Car {
    private String carId;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailabe;

    public Car(String carId, String brand, String model, double basePricePerDay){
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailabe = true;
    }


    public String getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double calculatePrice(int rentalDays) {
        return basePricePerDay + rentalDays;
    }

    public boolean isAvailabe() {
        return isAvailabe;
    }
    public  void rent(){
        isAvailabe = false;
    }
    public void returnCar(){
        isAvailabe = true;
    }
}
