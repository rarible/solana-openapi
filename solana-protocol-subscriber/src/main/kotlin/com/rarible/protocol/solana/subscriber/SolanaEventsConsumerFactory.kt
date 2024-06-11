package com.rarible.protocol.solana.subscriber

import com.rarible.core.kafka.RaribleKafkaConsumer
import com.rarible.core.kafka.json.JsonDeserializer
import com.rarible.protocol.solana.dto.ActivityDto
import com.rarible.protocol.solana.dto.BalanceEventDto
import com.rarible.protocol.solana.dto.CollectionEventDto
import com.rarible.protocol.solana.dto.OrderEventDto
import com.rarible.protocol.solana.dto.SolanaEventTopicProvider
import com.rarible.protocol.solana.dto.TokenEventDto
import com.rarible.protocol.solana.dto.TokenMetaEventDto
import org.apache.kafka.clients.consumer.OffsetResetStrategy
import java.util.UUID

class SolanaEventsConsumerFactory(
    private val brokerReplicaSet: String,
    host: String,
    private val environment: String,
    private val blockchain: String,
) {

    private val clientIdPrefix = "$environment.$host.${UUID.randomUUID()}"

    fun createTokenEventConsumer(
        consumerGroup: String,
        clientIdSuffix: String = ""
    ): RaribleKafkaConsumer<TokenEventDto> =
        RaribleKafkaConsumer(
            clientId = "$clientIdPrefix.$blockchain.consumer.token$clientIdSuffix",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = TokenEventDto::class.java,
            consumerGroup = consumerGroup,
            offsetResetStrategy = OffsetResetStrategy.EARLIEST,
            defaultTopic = SolanaEventTopicProvider.getTokenTopic(environment, blockchain),
            bootstrapServers = brokerReplicaSet
        )

    fun createTokenMetaEventConsumer(
        consumerGroup: String,
        clientIdSuffix: String = ""
    ): RaribleKafkaConsumer<TokenMetaEventDto> =
        RaribleKafkaConsumer(
            clientId = "$clientIdPrefix.$blockchain.consumer.token.meta$clientIdSuffix",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = TokenMetaEventDto::class.java,
            consumerGroup = consumerGroup,
            offsetResetStrategy = OffsetResetStrategy.EARLIEST,
            defaultTopic = SolanaEventTopicProvider.getTokenMetaTopic(environment, blockchain),
            bootstrapServers = brokerReplicaSet
        )

    fun createBalanceEventConsumer(
        consumerGroup: String,
        clientIdSuffix: String = ""
    ): RaribleKafkaConsumer<BalanceEventDto> =
        RaribleKafkaConsumer(
            clientId = "$clientIdPrefix.$blockchain.consumer.balance$clientIdSuffix",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = BalanceEventDto::class.java,
            consumerGroup = consumerGroup,
            offsetResetStrategy = OffsetResetStrategy.EARLIEST,
            defaultTopic = SolanaEventTopicProvider.getBalanceTopic(environment, blockchain),
            bootstrapServers = brokerReplicaSet
        )

    fun createCollectionEventConsumer(
        consumerGroup: String,
        clientIdSuffix: String = ""
    ): RaribleKafkaConsumer<CollectionEventDto> =
        RaribleKafkaConsumer(
            clientId = "$clientIdPrefix.$blockchain.consumer.collection$clientIdSuffix",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = CollectionEventDto::class.java,
            consumerGroup = consumerGroup,
            offsetResetStrategy = OffsetResetStrategy.EARLIEST,
            defaultTopic = SolanaEventTopicProvider.getCollectionTopic(environment, blockchain),
            bootstrapServers = brokerReplicaSet
        )

    fun createOrderEventConsumer(
        consumerGroup: String,
        clientIdSuffix: String = ""
    ): RaribleKafkaConsumer<OrderEventDto> =
        RaribleKafkaConsumer(
            clientId = "$clientIdPrefix.$blockchain.consumer.order$clientIdSuffix",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = OrderEventDto::class.java,
            consumerGroup = consumerGroup,
            offsetResetStrategy = OffsetResetStrategy.EARLIEST,
            defaultTopic = SolanaEventTopicProvider.getOrderTopic(environment, blockchain),
            bootstrapServers = brokerReplicaSet
        )

    fun createActivityEventConsumer(
        consumerGroup: String,
        clientIdSuffix: String = ""
    ): RaribleKafkaConsumer<ActivityDto> =
        RaribleKafkaConsumer(
            clientId = "$clientIdPrefix.$blockchain.consumer.activity$clientIdSuffix",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = ActivityDto::class.java,
            consumerGroup = consumerGroup,
            offsetResetStrategy = OffsetResetStrategy.EARLIEST,
            defaultTopic = SolanaEventTopicProvider.getActivityTopic(environment, blockchain),
            bootstrapServers = brokerReplicaSet
        )
}
