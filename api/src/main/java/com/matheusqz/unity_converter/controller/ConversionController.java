package com.matheusqz.unity_converter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.matheusqz.unity_converter.models.ConversionRequest;
import com.matheusqz.unity_converter.models.UnitValues.LengthUnit;
import com.matheusqz.unity_converter.models.UnitValues.TemperatureUnit;
import com.matheusqz.unity_converter.models.UnitValues.WeightUnit;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/convert")
@CrossOrigin
public class ConversionController {

    Gson gson = new Gson();

    @GetMapping("")
    public String testing() {
        return new String("Funcionando!");
    }

    @PostMapping("")
    public String convert(@RequestBody String entity) {
        System.out.println(entity);
        ConversionRequest request = gson.fromJson(entity, ConversionRequest.class);
        switch (request.unitType()) {
            case "Length":
                return gson.toJson(LengthUnit.fromString(request.firstUnit()).convertTo(request));
            case "Weight":
                return gson.toJson(WeightUnit.fromString(request.firstUnit()).convertTo(request));
            case "Temperature":
                return gson.toJson(TemperatureUnit.fromString(request.firstUnit()).convertTo(request));
            default:
                return "Tipo de unidade desconhecido: " + entity;
        }
    }
}
