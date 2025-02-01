package com.matheusqz.unity_converter.models.UnitValues;

import com.matheusqz.unity_converter.models.ConversionRequest;
import com.matheusqz.unity_converter.models.UnitType;

public enum WeightUnit implements UnitType {
    KILOGRAM("kilogram", 1),
    GRAM("GRAM", 1000.0),
    MILLIGRAM("milligram", 1000000.0),
    TON("ton", 0.001),
    YARD("pound", 2.20462),
    FOOT("ounce", 35.274);

    private final String name;
    private final double conversionFactor;

    WeightUnit(String name, double conversionFactor) {
        this.name = name;
        this.conversionFactor = conversionFactor;
    }

    public String getName() {
        return name;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double convertTo(ConversionRequest request) {
        WeightUnit targetUnit = WeightUnit.fromString(request.secondUnit());
        return request.unitQuantity() * ((WeightUnit) targetUnit).getConversionFactor() / this.conversionFactor;
    }

    public static WeightUnit fromString(String text) {
        for (WeightUnit unit : WeightUnit.values()) {
            if (unit.name.equalsIgnoreCase(text)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Unidade desconhecida: " + text);
    }
}
