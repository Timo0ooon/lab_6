package com.ClientServerApp.Model.Car;

import com.ClientServerApp.Model.ValidatorInterface.StringValidator;

import java.io.Serial;
import java.io.Serializable;

import static com.ClientServerApp.MyInput.MyInput.input;

public class Car implements Serializable {
    @Serial
    private static final long serialVersionUID = 1001L;
    private String name;  // Field cannot be null

    public Car() {
        this.set_car_name((String name) -> !name.replaceAll(" ", "").isEmpty());
    }

    private void set_car_name(StringValidator validator) {

        while (true) {
            System.out.print("Write car name: ");
            this.name = input();
            if (validator.check(this.name)) {
                break;
            }
            System.out.print("Car name can not be empty! ");
        }
    }

    @Override
    public String toString() { return "Car name: " + this.name; }
}
