package com.rarible.protocol.solana.dto

class SolanaEventTopicProvider {

    companion object {

        /**
         * Added to the message headers by Solana events Kafka producer.
         */
        const val VERSION = "v1"

        fun getTokenTopic(environment: String): String =
            "protocol.$environment.solana.token"

        fun getTokenMetaTopic(environment: String): String =
            "protocol.$environment.solana.token.meta"

        fun getBalanceTopic(environment: String): String =
            "protocol.$environment.solana.balance"

        fun getCollectionTopic(environment: String): String =
            "protocol.$environment.solana.collection"

        fun getOrderTopic(environment: String): String =
            "protocol.$environment.solana.order"

        fun getActivityTopic(environment: String): String =
            "protocol.$environment.solana.activity"

    }
}
