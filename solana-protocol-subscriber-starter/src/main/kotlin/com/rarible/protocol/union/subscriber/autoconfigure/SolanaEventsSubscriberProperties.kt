package com.rarible.protocol.union.subscriber.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("solana.protocol.subscriber")
@ConstructorBinding
data class SolanaEventsSubscriberProperties(
    val brokerReplicaSet: String
)
