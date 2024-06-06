package com.rarible.protocol.solana.subscriber.autoconfigure

import com.rarible.core.application.ApplicationEnvironmentInfo
import com.rarible.protocol.solana.subscriber.SolanaEventsConsumerFactory
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

@SpringBootTest(
    properties = [
        "solana.protocol.subscriber.brokerReplicaSet=PLAINTEXT://localhost:9092",
        "solana.protocol.subscriber.blockchain=solana"
    ]
)
@SpringBootConfiguration
@EnableAutoConfiguration
@Import(SolanaEventsSubscriberAutoConfigurationIt.Configuration::class)
class SolanaEventsSubscriberAutoConfigurationIt {

    @Autowired
    private lateinit var solanaEventsConsumerFactory: SolanaEventsConsumerFactory

    @Test
    fun `test default consumer initialized`() {
        Assertions.assertThat(solanaEventsConsumerFactory).isNotNull
    }

    @TestConfiguration
    internal class Configuration {
        @Bean
        fun applicationEnvironmentInfo(): ApplicationEnvironmentInfo {
            return ApplicationEnvironmentInfo("test", "test.com")
        }
    }
}
