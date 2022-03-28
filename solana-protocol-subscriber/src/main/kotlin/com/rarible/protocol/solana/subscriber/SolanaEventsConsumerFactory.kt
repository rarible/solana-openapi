package com.rarible.protocol.solana.subscriber

import com.rarible.core.kafka.RaribleKafkaConsumer
import com.rarible.core.kafka.json.JsonDeserializer
import com.rarible.protocol.solana.dto.ActivityDto
import com.rarible.protocol.solana.dto.BalanceEventDto
import com.rarible.protocol.solana.dto.CollectionEventDto
import com.rarible.protocol.solana.dto.OrderEventDto
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

    fun createCollectionEventConsumer(consumerGroup: String): RaribleKafkaConsumer<CollectionEventDto> =
        RaribleKafkaConsumer(
            clientId = "$clientIdPrefix.solana.consumer.collection",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = CollectionEventDto::class.java,
            consumerGroup = consumerGroup,
            defaultTopic = SolanaEventTopicProvider.getCollectionTopic(environment),
            bootstrapServers = brokerReplicaSet
        )

    fun createOrderEventConsumer(consumerGroup: String): RaribleKafkaConsumer<OrderEventDto> =
        RaribleKafkaConsumer(
            clientId = "$clientIdPrefix.solana.consumer.order",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = OrderEventDto::class.java,
            consumerGroup = consumerGroup,
            defaultTopic = SolanaEventTopicProvider.getOrderTopic(environment),
            bootstrapServers = brokerReplicaSet
        )

    fun createActivityEventConsumer(consumerGroup: String): RaribleKafkaConsumer<ActivityDto> =
        RaribleKafkaConsumer(
            clientId = "$clientIdPrefix.solana.consumer.activity",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = ActivityDto::class.java,
            consumerGroup = consumerGroup,
            defaultTopic = SolanaEventTopicProvider.getActivityTopic(environment),
            bootstrapServers = brokerReplicaSet
        )
}
