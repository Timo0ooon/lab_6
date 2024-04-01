package com.ClientServerApp.Model.Coordinates;

import com.ClientServerApp.Model.Coordinates.Validators.YCoordinateValidator;

import java.io.Serial;
import java.io.Serializable;

import static com.ClientServerApp.MyInput.MyInput.input;

public class Coordinates implements Serializable {
    @Serial
    private static final long serialVersionUID = 1002L;
    private Double x;
    private Long y; // Field cannot be null. Value must be greater than -507

    /**
     * Constructor consistently requests coordinates from the user.
     * The constructor will not stop until the coordinate is greater than -507.
     */
    public Coordinates() {
        this.set_X_coordinate_value();
        this.set_Y_coordinate_value();
    }

    private void set_X_coordinate_value() {
        while (true) {
            try {
                System.out.print("Write coordinate X: ");
                this.x = Double.parseDouble(input());
                break;
            }
            catch (NumberFormatException e) {
                System.out.print("Write a number! ");

            }
        }
    }

    private void set_Y_coordinate_value() {
        YCoordinateValidator validator = new YCoordinateValidator();

        while (true) {
            try {
                System.out.print("Write coordinate Y: ");
                this.y = Long.parseLong(input());

                if (validator.check(this.y))
                    break;

                System.out.print("Write a value greater than " + validator.value + "! ");

            }

            catch (NumberFormatException e) {
                System.out.print("Please write a number! ");
            }
        }
    }

    @Override
    public String toString() {
        return "X coordinate: " + this.x + "\n" + "Y coordinate: " + this.y;
    }

}