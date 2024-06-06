package com.rarible.protocol.solana.dto

class SolanaEventTopicProvider {

    companion object {

        /**
         * Added to the message headers by Solana events Kafka producer.
         */
        const val VERSION = "v1"

        fun getTokenTopic(environment: String, blockchain: String): String =
            "protocol.$environment.$blockchain.token"

        fun getTokenMetaTopic(environment: String, blockchain: String): String =
            "protocol.$environment.$blockchain.token.meta"

        fun getBalanceTopic(environment: String, blockchain: String): String =
            "protocol.$environment.$blockchain.balance"

        fun getCollectionTopic(environment: String, blockchain: String): String =
            "protocol.$environment.$blockchain.collection"

        fun getOrderTopic(environment: String, blockchain: String): String =
            "protocol.$environment.$blockchain.order"

        fun getActivityTopic(environment: String, blockchain: String): String =
            "protocol.$environment.$blockchain.activity"

    }
}
