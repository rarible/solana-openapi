package com.rarible.protocol.union.subscriber

import com.rarible.core.kafka.RaribleKafkaConsumer
import com.rarible.core.kafka.json.JsonDeserializer
import com.rarible.solana.protocol.dto.SolanaEventTopicProvider
import com.rarible.solana.protocol.dto.TokenEventDto
import java.util.*

class SolanaEventsConsumerFactory(
    private val brokerReplicaSet: String,
    host: String,
    private val environment: String
) {

    private val clientIdPrefix = "$environment.$host.${UUID.randomUUID()}"

    fun createTokenEventConsumer(consumerGroup: String): RaribleKafkaConsumer<TokenEventDto> =
        RaribleKafkaConsumer(
            clientId = "$clientIdPrefix.union-item-consumer",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = TokenEventDto::class.java,
            consumerGroup = consumerGroup,
            defaultTopic = SolanaEventTopicProvider.getTokenTopic(environment),
            bootstrapServers = brokerReplicaSet
        )
}
