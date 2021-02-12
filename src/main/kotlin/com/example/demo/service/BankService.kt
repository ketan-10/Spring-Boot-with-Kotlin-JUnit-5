package com.example.demo.service

import com.example.demo.datasource.BankDataSource
import com.example.demo.model.Bank
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class BankService(@Qualifier("mock") val bankDataSource: BankDataSource) {

    fun getBanks(): Collection<Bank> {
        return bankDataSource.retrieveBanks()
    }

    fun getBank(accountNumber: String) : Bank{
        return bankDataSource.retrieveBank(accountNumber)
    }

    fun addBank(bank: Bank): Bank {
        return bankDataSource.addBank(bank)
    }

    fun updateBank(bank: Bank): Bank {
        return bankDataSource.updateBank(bank)
    }

}