package com.matheusqz.unity_converter.models.UnitValues;

import com.matheusqz.unity_converter.models.ConversionRequest;
import com.matheusqz.unity_converter.models.UnitType;

public enum TemperatureUnit implements UnitType {
    CELSIUS("celsius") {
        @Override
        public double toCelsius(double value) {
            return value;
        }

        @Override
        public double fromCelsius(double value) {
            return value;
        }
    },
    FAHRENHEIT("fahrenheit") {
        @Override
        public double toCelsius(double value) {
            return (value - 32) * 5 / 9;
        }

        @Override
        public double fromCelsius(double value) {
            return (value * 9 / 5) + 32;
        }
    },
    KELVIN("kelvin") {
        @Override
        public double toCelsius(double value) {
            return value - 273.15;
        }

        @Override
        public double fromCelsius(double value) {
            return value + 273.15;
        }
    };

    private final String name;

    TemperatureUnit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double toCelsius(double value);
    public abstract double fromCelsius(double value);

    @Override
    public double convertTo(ConversionRequest request) {
        TemperatureUnit targetUnit = TemperatureUnit.fromString(request.secondUnit());
        double valueInCelsius = this.toCelsius(request.unitQuantity());
        return targetUnit.fromCelsius(valueInCelsius);
    }

    public static TemperatureUnit fromString(String text) {
        for (TemperatureUnit unit : TemperatureUnit.values()) {
            if (unit.name.equalsIgnoreCase(text)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Unidade desconhecida: " + text);
    }
}
