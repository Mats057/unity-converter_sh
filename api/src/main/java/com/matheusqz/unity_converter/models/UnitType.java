package com.matheusqz.unity_converter.models;

public interface UnitType {
    String getName();
    double convertTo(ConversionRequest request);
}
