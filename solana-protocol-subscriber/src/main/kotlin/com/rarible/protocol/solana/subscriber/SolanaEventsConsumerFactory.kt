package com.rarible.protocol.solana.subscriber

import com.rarible.core.kafka.RaribleKafkaConsumer
import com.rarible.core.kafka.json.JsonDeserializer
import com.rarible.protocol.solana.dto.BalanceEventDto
import com.rarible.protocol.solana.dto.SolanaEventTopicProvider
import com.rarible.protocol.solana.dto.TokenEventDto
import java.util.*

class SolanaEventsConsumerFactory(
    private val brokerReplicaSet: String,
    host: String,
    private val environment: String
) {

    private val clientIdPrefix = "$environment.$host.${UUID.randomUUID()}"

    fun createTokenEventConsumer(consumerGroup: String): RaribleKafkaConsumer<TokenEventDto> =
        RaribleKafkaConsumer(
            clientId = "$clientIdPrefix.solana.consumer.token",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = TokenEventDto::class.java,
            consumerGroup = consumerGroup,
            defaultTopic = SolanaEventTopicProvider.getTokenTopic(environment),
            bootstrapServers = brokerReplicaSet
        )

    fun createBalanceEventConsumer(consumerGroup: String): RaribleKafkaConsumer<BalanceEventDto> =
        RaribleKafkaConsumer(
            clientId = "$clientIdPrefix.solana.consumer.balance",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = BalanceEventDto::class.java,
            consumerGroup = consumerGroup,
            defaultTopic = SolanaEventTopicProvider.getBalanceTopic(environment),
            bootstrapServers = brokerReplicaSet
        )
}