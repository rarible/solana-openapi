package com.rarible.protocol.solana.dto

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

        fun getCollectionTopic(environment: String): String =
            "solana.protocol.$environment.collection"

        fun getOrderTopic(environment: String): String =
            "solana.protocol.$environment.order"

        fun getActivityTopic(environment: String): String =
            "solana.protocol.$environment.activity"

    }
}
