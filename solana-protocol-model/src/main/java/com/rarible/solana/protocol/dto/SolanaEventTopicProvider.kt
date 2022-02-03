package com.rarible.solana.protocol.dto

class SolanaEventTopicProvider {

    companion object {

        /**
         * Added to the message headers by Solana events Kafka producer.
         */
        const val VERSION = "v1"

        fun getTokenTopic(environment: String): String =
            "solana.protocol.$environment.token"

        fun getBalanceTopic(environment: String): String =
            "solana.protocol.$environment.balance"

    }
}
