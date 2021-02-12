package com.example.demo.datasource.network

import com.example.demo.datasource.BankDataSource
import com.example.demo.datasource.network.datatransferobject.NetworkBankObject
import com.example.demo.model.Bank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import java.io.IOException

@Repository("network")
class NetworkBankDataSource(@Autowired private val restTemplate: RestTemplate) : BankDataSource{

    override fun retrieveBanks(): Collection<Bank> {
        val response = restTemplate.getForEntity<NetworkBankObject>("http://54.193.31.159/banks",)
        return response.body?.results?: throw IOException("Could not fetch banks")

    }

    override fun retrieveBank(accountNumber: String): Bank {
        TODO("Not yet implemented")
    }

    override fun addBank(bank: Bank): Bank {
        TODO("Not yet implemented")
    }

    override fun updateBank(bank: Bank): Bank {
        TODO("Not yet implemented")
    }
}