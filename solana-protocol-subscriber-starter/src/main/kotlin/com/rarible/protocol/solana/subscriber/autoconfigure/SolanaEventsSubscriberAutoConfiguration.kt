package com.rarible.protocol.solana.subscriber.autoconfigure

import com.rarible.core.application.ApplicationEnvironmentInfo
import com.rarible.protocol.solana.subscriber.SolanaEventsConsumerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean

@EnableConfigurationProperties(SolanaEventsSubscriberProperties::class)
class SolanaEventsSubscriberAutoConfiguration(
    private val applicationEnvironmentInfo: ApplicationEnvironmentInfo,
    private val properties: SolanaEventsSubscriberProperties
) {

    @Bean
    @ConditionalOnMissingBean(SolanaEventsConsumerFactory::class)
    fun unionEventsConsumerFactory(): SolanaEventsConsumerFactory {
        return SolanaEventsConsumerFactory(
            brokerReplicaSet = properties.brokerReplicaSet,
            host = applicationEnvironmentInfo.host,
            environment = applicationEnvironmentInfo.name
        )
    }
}
