package com.pichincha.exchange.controller;

import com.pichincha.exchange.model.dto.request.OperationRequestDTO;
import com.pichincha.exchange.model.dto.response.ExchangeResponse;
import com.pichincha.exchange.persistence.entity.Operation;
import com.pichincha.exchange.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Mono;


@RestController
@RequestMapping(value = "/experience")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

   @PostMapping(value = "/exchange")
   public Mono<ExchangeResponse> cambiar(@RequestBody OperationRequestDTO requestDTO){
       return experienceService.generate(requestDTO);
   }



}
