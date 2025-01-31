package com.matheusqz.unity_converter.models;

public interface Unit {
    String getName();
    double convertTo(ConversionRequest request);
}
