package com.example.demo.datasource.mock

import com.example.demo.datasource.BankDataSource
import com.example.demo.model.Bank
import org.springframework.stereotype.Repository

@Repository("mock")
class MockBankDataSource : BankDataSource {

    val banks = mutableListOf(
        Bank("1234", 23.123, 15),
        Bank("2342", 23.123, 15),
        Bank("9256", 23.123, 15),
    )

    override fun retrieveBanks(): Collection<Bank> {
        return banks
    }

    override fun retrieveBank(accountNumber: String): Bank {
        return banks.firstOrNull{
            it.accountNumber == accountNumber
        } ?: throw NoSuchElementException("No such Bank with given account exists")
    }

    override fun addBank(bank: Bank): Bank {
        banks.add(bank)
        return bank;
    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank = banks.firstOrNull{
            it.accountNumber == bank.accountNumber
        } ?: throw NoSuchElementException("No such Bank with given account exists")

        banks.remove(currentBank)
        banks.add(bank)

        return bank;
    }
}