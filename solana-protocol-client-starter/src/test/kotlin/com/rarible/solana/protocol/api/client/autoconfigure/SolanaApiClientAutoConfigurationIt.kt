package com.rarible.solana.protocol.api.client.autoconfigure

import com.rarible.core.application.ApplicationEnvironmentInfo
import com.rarible.solana.protocol.api.client.SolanaApiServiceUriProvider
import com.rarible.solana.protocol.api.client.SolanaNftIndexerApiClientFactory
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

@SpringBootTest
@SpringBootConfiguration
@EnableAutoConfiguration
@Import(SolanaApiClientAutoConfigurationIt.Configuration::class)
class SolanaApiClientAutoConfigurationIt {

    @Autowired
    private lateinit var solanaApiServiceUriProvider: SolanaApiServiceUriProvider

    @Autowired
    private lateinit var solanaNftIndexerApiClientFactory: SolanaNftIndexerApiClientFactory

    @Autowired
    @Qualifier(SOLANA_WEB_CLIENT_CUSTOMIZER)
    private lateinit var webClientCustomizer: WebClientCustomizer

    @Test
    fun `test default clients initialized`() {
        every { webClientCustomizer.customize(any()) } returns Unit
        solanaNftIndexerApiClientFactory.createTokenControllerApiClient()
        solanaNftIndexerApiClientFactory.createBalanceControllerApiClient()
        verify { webClientCustomizer.customize(any()) }
    }

    @Test
    fun `test default client uri`() {
        val uri = solanaApiServiceUriProvider.getUri()
        assertThat(uri.toString()).isEqualTo("http://test-solana-api:8080")
    }

    @TestConfiguration
    internal class Configuration {

        @Bean
        @Qualifier(SOLANA_WEB_CLIENT_CUSTOMIZER)
        fun webClientCustomizer(): WebClientCustomizer = mockk()

        @Bean
        fun applicationEnvironmentInfo(): ApplicationEnvironmentInfo =
            ApplicationEnvironmentInfo("test", "test.com")
    }
}
