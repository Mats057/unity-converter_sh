package com.matheusqz.unity_converter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.matheusqz.unity_converter.models.ConversionRequest;
import com.matheusqz.unity_converter.models.UnitType;
import com.matheusqz.unity_converter.services.UnitTypeConverterService;

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
        return new String("Working!");
    }

    @PostMapping("")
    public String convert(@RequestBody String entity) {
        System.out.println(entity);
        ConversionRequest request = gson.fromJson(entity, ConversionRequest.class);

        try {
            UnitType unitType = UnitTypeConverterService.getUnitByName(request.firstUnit(), request.unitType());
            return gson.toJson(unitType.convertTo(request));
        } catch (IllegalArgumentException e) {
            return "Erro: " + e.getMessage();
        }
    }
}
