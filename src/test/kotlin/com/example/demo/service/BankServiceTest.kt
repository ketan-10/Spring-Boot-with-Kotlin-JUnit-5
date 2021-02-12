package com.example.demo.service

import com.example.demo.datasource.mock.MockBankDataSource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class BankServiceTest{

    private val bankDataSource: MockBankDataSource = mockk(relaxed = true)
    private val bankService: BankService = BankService(bankDataSource)

    @Test
    fun `should call its data source to retrieve banks`(){
        // given



        // when
        val banks = bankService.getBanks()

        // then

        verify(exactly = 1){
            bankDataSource.retrieveBanks()
        }
    }


}