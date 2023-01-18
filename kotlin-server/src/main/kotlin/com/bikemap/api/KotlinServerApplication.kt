package com.bikemap.api

import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@Slf4j
@SpringBootApplication
class KotlinServerApplication

fun main(args: Array<String>) {
    runApplication<KotlinServerApplication>(*args)

    val log: Logger = LoggerFactory.getLogger(KotlinServerApplication::class.java)
    log.info("Hi I'm {} log", "INFO")
}
