package com.example.demo

import javax.persistence.*
@Entity
@Table(name = "Patient_Table")
data class PatientTable(

        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long,

        )