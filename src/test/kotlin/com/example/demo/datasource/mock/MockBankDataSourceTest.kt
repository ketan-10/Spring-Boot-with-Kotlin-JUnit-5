package com.example.demo.datasource.mock

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest{

    private val mockDataSource: MockBankDataSource = MockBankDataSource()

    @Test
    fun `should provide a collection of banks`(){
        // given
        
        // when 
        val banks = mockDataSource.retrieveBanks()
        
        // then
        assertThat(banks).isNotEmpty

    }

    @Test
    fun `should provide some mock data`(){
        // given

        // when
        val banks = mockDataSource.retrieveBanks()

        // then
        assertThat(banks).allMatch{ it.accountNumber.isNotBlank() }
        assertThat(banks).anyMatch{ it.trust != 0.0 }
        assertThat(banks).anyMatch{ it.transactionFee != 0 }

    }
}