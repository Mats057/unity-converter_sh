package com.matheusqz.unity_converter.models;

import com.google.gson.annotations.SerializedName;


public record ConversionRequest(@SerializedName("first-unit") String firstUnit,
        @SerializedName("second-unit") String secondUnit, @SerializedName("unit-quantity") double unitQuantity,
        @SerializedName("unit-type") String unitType) {
}
