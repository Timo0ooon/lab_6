package com.ClientServerApp.Model.HumanBeing.Validators;

import com.ClientServerApp.Model.ValidatorInterface.NumberValidator;

public class ImpactSpeedValidator implements NumberValidator {
    public final int value = -355;
    @Override
    public boolean check(Number value) {
        return ((int) value >= -355);
    }
}
