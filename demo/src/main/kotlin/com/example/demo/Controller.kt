package com.example.demo

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Id

@RestController
class Controller(private val patientRepository: PatientRepository)
{
    fun get(@PathVariable  id : Long) {
    }
}