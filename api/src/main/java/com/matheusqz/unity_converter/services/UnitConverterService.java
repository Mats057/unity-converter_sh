package com.matheusqz.unity_converter.services;

import com.matheusqz.unity_converter.models.Unit;
import com.matheusqz.unity_converter.models.UnitValues.LengthUnit;
import com.matheusqz.unity_converter.models.UnitValues.TemperatureUnit;
import com.matheusqz.unity_converter.models.UnitValues.WeightUnit;

public class UnitConverterService {

    public static Unit getUnitByName(String unitName, String unitType) {
        switch (unitType) {
            case "Length":
                return LengthUnit.fromString(unitName);
            case "Weight":
                return WeightUnit.fromString(unitName);
            case "Temperature":
                return TemperatureUnit.fromString(unitName);
            default:
                throw new IllegalArgumentException("Tipo de unidade desconhecido: " + unitType);
        }
    }
}
