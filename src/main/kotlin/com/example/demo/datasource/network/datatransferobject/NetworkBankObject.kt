package com.example.demo.datasource.network.datatransferobject

import com.example.demo.model.Bank
/*
* {
    "count": 8,
    "next": null,
    "previous": null,
    "results": [
        {
            "account_number": "14be9505e28bdbd872cc6038c990e93cf02066fa922bb08ba97974fa951e9e22",
            "ip_address": "54.193.31.159",
            "node_identifier": "6873729b23663df345c9a03d8b9b0686f245f4cab6b869eb60d794b81bfd33bb",
            "port": 80,
            "protocol": "http",
            "version": "v1.0",
            "default_transaction_fee": 1,
            "trust": "100.00"
        },
    ]
}
* */
data class NetworkBankObject (
    val results: Collection<Bank>

)