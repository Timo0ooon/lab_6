package com.ClientServerApp.Model.HumanBeing;

import com.ClientServerApp.Model.Car.Car;
import com.ClientServerApp.Model.Coordinates.Coordinates;
import com.ClientServerApp.Model.DataBase.Identifiers;
import com.ClientServerApp.Model.Enums.Mood;
import com.ClientServerApp.Model.Enums.WeaponType;
import com.ClientServerApp.Model.HumanBeing.Validators.ImpactSpeedValidator;
import com.ClientServerApp.Model.ValidatorInterface.StringValidator;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

import static com.ClientServerApp.MyInput.MyInput.input;

public class HumanBeing implements Comparable<HumanBeing>, Serializable {
    @Serial
    private static final long serialVersionUID = 1003L;

    public Integer id;  // Field can not be null. Value must be greater than 0. Value must be unique, The value should be generated automatically.
    public String name;  // Field can not be null. Value can not be empty line.
    public Coordinates coordinates;  // Field can not be null.
    public LocalDate creationDate;  // Field can not be null. The value should be generated automatically.
    public Boolean realHero;  // Field can not be null.
    public Boolean hasToothpick;  // Field can not be null.
    public Integer impactSpeed;  // Value must be greater than -355. Field can not be null.
    public WeaponType weaponType;  // Field can not be null.
    public Mood mood;  // Field can not be null.
    public Car car;  // Field can not be null.

    public HumanBeing() {
        this.setID();
        this.setName((String name) -> !name.replaceAll(" ", "").isEmpty());
        this.setCoordinates();
        this.setDate();
        this.setRealHero();
        this.setHasToothpick();
        this.setImpactSpeed();
        this.setWeaponType();
        this.setMood();
        this.setCar();
    }

    private void setID () {
        this.id = Identifiers.generate();
    }

    private void setName(StringValidator validator) {

        while (true) {

            System.out.print("Write name: ");
            this.name = input();

            if (validator.check(this.name))
                break;

            System.out.print("Name can not be empty! ");

        }
    }

    private void setCoordinates() {
        this.coordinates = new Coordinates();
    }

    private void setDate() {
        this.creationDate = LocalDate.now();
    }

    private void setRealHero() {
        while (true) {
            System.out.print("Is this person real? Write true or false: ");
            String user_line = input();
            if (user_line.trim().equalsIgnoreCase("false") || user_line.trim().equalsIgnoreCase("f")) {
                this.realHero = false;
                break;
            }
            else if (user_line.trim().equalsIgnoreCase("true") || user_line.trim().equalsIgnoreCase("t")) {
                this.realHero = true;
                break;
            }
            else {
                System.out.print("Write values: true of false! ");
            }
        }
    }

    private void setHasToothpick() {
        while (true) {

            System.out.print("Does this person have a toothpick? Write true or false: ");

            String user_line = input();
            if (user_line.trim().equalsIgnoreCase("false") || user_line.trim().equalsIgnoreCase("f")) {
                this.hasToothpick = false;
                break;
            }
            else if (user_line.trim().equalsIgnoreCase("true") || user_line.trim().equalsIgnoreCase("t")) {
                this.hasToothpick = true;
                break;
            }
            else {
                System.out.print("Write values: true of false! ");
            }
        }
    }

    private void setImpactSpeed() {
        ImpactSpeedValidator validator = new ImpactSpeedValidator();
        while (true) {
            try {
                System.out.print("Write impactSpeed: ");
                this.impactSpeed = Integer.parseInt(input());
                if (validator.check(this.impactSpeed)) {
                    break;
                }
                System.out.print("Value must be greater than or equal to " + validator.value + "! ");
            }

            catch(NumberFormatException e) {
                System.out.print("Value must be integer! ");
            }
        }
    }

    private void setWeaponType() {
        while (true) {
            StringBuilder sentence = new StringBuilder("Select the number from this list that you want to set:\n");
            WeaponType[] values = WeaponType.values();

            Arrays.stream(values).forEach(el -> sentence.append(Arrays.asList(values).indexOf(el) + 1).append(". ").append( el.name() ).append("\n") );

            System.out.println(sentence);
            System.out.print("Write: ");

            try {
                int user_value = Integer.parseInt(input());
                if ((1 <= user_value) && (user_value <= values.length)) {
                    this.weaponType = values[user_value - 1];
                    break;
                }
                System.out.print("Value must be greater than 1 and less than " + values.length + ". ");
            }

            catch (NumberFormatException e) {
                System.out.print("Value must be integer! ");
            }
        }
    }

    private void setMood() {
        while (true) {
            StringBuilder sentence = new StringBuilder("Select the number from this list that you want to set:\n");
            Mood[] values = Mood.values();

            Arrays.stream(values).forEach(el -> {sentence.append(Arrays.asList(values).indexOf(el) + 1).append(". ").append( el.name() ).append("\n"); } );
            System.out.println(sentence);
            System.out.print("Write: ");

            try {
                int user_value = Integer.parseInt(input());
                if ((1 <= user_value) && (user_value <= values.length)) {
                    this.mood = values[user_value - 1];
                    break;
                }
                System.out.print("Value must be greater than 1 and less than " + values.length + ". ");
            }

            catch (NumberFormatException e) {
                System.out.print("Value must be integer! ");
            }
        }
    }

    private void setCar() {
        this.car = new Car();
    }

    public Integer getId() {return this.id; }

    public Integer getImpactSpeed() { return this.impactSpeed; }

    public LocalDate getCreationDate() { return this.creationDate; }

    public boolean getHasToothpick() { return this.hasToothpick; }

    public Car getCar() { return car; }

    public Coordinates getCoordinates() { return coordinates; }

    public Mood getMood() { return mood; }

    public String getName() { return name; }

    public Boolean getRealHero() { return realHero; }

    public WeaponType getWeaponType() { return weaponType; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumanBeing that = (HumanBeing) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(coordinates, that.coordinates) && Objects.equals(creationDate, that.creationDate) && Objects.equals(realHero, that.realHero) && Objects.equals(hasToothpick, that.hasToothpick) && Objects.equals(impactSpeed, that.impactSpeed) && weaponType == that.weaponType && mood == that.mood && Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, weaponType, mood, car);
    }

    @Override
    public String toString() {
        return  "ID: " + this.id + "\n" + "Name: " + this.name + "\n" + this.coordinates + "\n" + "Creation date: " + this.creationDate +
                "\n" + "Real hero: " + this.realHero + "\n" + "Has toothpick: " + this.hasToothpick + "\n" +
                "Impact speed: " + this.impactSpeed + "\n" + "Weapon type: " + this.weaponType + "\n" + "Mood:" + this.mood + "\n" + this.car;
    }

    @Override
    public int compareTo(HumanBeing other_human) {
        return this.id.compareTo(other_human.getId());
    }
}
