package com.matheusqz.unity_converter.models.UnitValues;

import com.matheusqz.unity_converter.models.ConversionRequest;
import com.matheusqz.unity_converter.models.UnitType;

public enum LengthUnit implements UnitType{
    METER("meter", 1.0),
    KILOMETER("kilometer", 0.001),
    CENTIMETER("centimeter", 100.0),
    MILLIMETER("millimeter", 1000.0),
    MILE("mile", 0.000621371),
    YARD("yard", 1.09361),
    FOOT("foot", 3.28084),
    INCH("inch", 39.3701);

    private final String name;
    private final double conversionFactor;

    LengthUnit(String name, double conversionFactor) {
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
        LengthUnit targetUnit = LengthUnit.fromString(request.secondUnit());
        return request.unitQuantity() * ((LengthUnit) targetUnit).getConversionFactor() / this.conversionFactor;
    }

    public static LengthUnit fromString(String text) {
        for (LengthUnit unit : LengthUnit.values()) {
            if (unit.name.equalsIgnoreCase(text)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Unidade desconhecida: " + text);
    }
}
