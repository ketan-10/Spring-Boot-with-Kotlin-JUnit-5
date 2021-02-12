package com.example.demo.controller

import com.example.demo.model.Bank
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post


@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    var mockMvc: MockMvc,
    var objectMapper: ObjectMapper
) {

    val baseUrl = "/api/banks"

    @Nested
    @DisplayName("GetBank()")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBanks{
        @Test
        fun `should return all banks`(){
            // when/ten
            mockMvc.get(baseUrl)
                .andDo { print(); }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                    }
                    jsonPath("$[0].account_number"){
                        value("1234")
                    }
                }
        }


    }

    @Nested
    @DisplayName("GetBank()")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBank{

        @Test
        fun `should return specific bank`(){

            val accountNumber = "1234"
            mockMvc.get("${baseUrl}/${accountNumber}")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                    }
                    jsonPath("$.account_number"){
                        value(accountNumber)
                    }
                }
        }

        @Test
        fun `should return NOT FOUND if account number does not exists`(){

            val accountNumber = "not_existing_account"
            mockMvc.get("${baseUrl}/${accountNumber}")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }

                }

        }
    }


    @Nested
    @DisplayName("POST /api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PostNewBank{
        @Test
        fun `should create a new Bank`(){

            //given
            val newBank = Bank("postTestAccount", 13.23, 2)

            // when
            val performPost = mockMvc.post(baseUrl){
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }
            // then
            performPost
                .andDo { print(); }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                    }
                    jsonPath("$.account_number"){
                        value(newBank.accountNumber)
                    }
                }
        }

    }


    @Nested
    @DisplayName("PATCH /api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PatchBank(){

        @Test
        fun `should update a bank`(){

            //given
            val updateBank = Bank("2342", 30.40, 30)

            mockMvc.patch(baseUrl){
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updateBank)
            }
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updateBank))
                    }
                }

            // check if bank updated
            mockMvc.get("${baseUrl}/${updateBank.accountNumber}")
                .andExpect { content { json(objectMapper.writeValueAsString(updateBank)) } }
        }
    }


}