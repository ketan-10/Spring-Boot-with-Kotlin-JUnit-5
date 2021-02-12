package com.example.demo.controller

import com.example.demo.model.Bank
import com.example.demo.service.BankService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import java.net.http.HttpTimeoutException

@RestController
@RequestMapping("/api/banks")
class BankController(private  val bankService: BankService) {

    //@Autowired
    //lateinit var bankService: BankService


    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(exception: NoSuchElementException): ResponseEntity<String>{
        return ResponseEntity(exception.message, HttpStatus.NOT_FOUND)
    }

    @GetMapping
    fun getBanks(): Collection<Bank> = bankService.getBanks()

    @GetMapping("/{accountNumber}")
    fun getBank(@PathVariable accountNumber: String) : Bank{
        return bankService.getBank(accountNumber)
    }

    @PostMapping
    fun addBank(@RequestBody bank: Bank): Bank{
        return bankService.addBank(bank)
    }

    @PatchMapping
    fun updateBank(@RequestBody bank: Bank): Bank{
        return bankService.updateBank(bank)
    }
}


/**
 * Controller Provides endpoint -> Calls Services
 * Data Transfer -> Model objects
 *
 * Services Might be interdependent
 *
 * Services Talk with Repository to fetch data
 * Repository and Services transfer by -> Entity objects
 *
 * Repository can have multiple instances to fetch data from different paths (rest or db etc)
 *
 *
 * Other way to look at it
 *
 * Repository fetch the data in Entity classes
 * Services calls Repository to fetch
 * Services perform business logic on that data (might call different services too, depending on architecture)
 * Services collect the data in Model object and send it to Controller
 * Controller then Collect (just collect no business logic) send that data to View
 * Using transfer object as ViewModel
 *
 * https://softwareengineering.stackexchange.com/questions/304169/referencing-database-values-in-business-logic
 *
 */