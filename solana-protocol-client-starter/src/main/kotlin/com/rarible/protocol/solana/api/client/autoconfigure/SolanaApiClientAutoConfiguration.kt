package com.rarible.protocol.solana.api.client.autoconfigure

import com.rarible.core.application.ApplicationEnvironmentInfo
import com.rarible.protocol.solana.api.client.CompositeWebClientCustomizer
import com.rarible.protocol.solana.api.client.DefaultSolanaWebClientCustomizer
import com.rarible.protocol.solana.api.client.K8SSolanaApiServiceUriProvider
import com.rarible.protocol.solana.api.client.NoopWebClientCustomizer
import com.rarible.protocol.solana.api.client.SolanaApiServiceUriProvider
import com.rarible.protocol.solana.api.client.SolanaNftIndexerApiClientFactory
import com.rarible.protocol.solana.api.client.SwarmSolanaApiServiceUriProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer
import org.springframework.context.annotation.Bean

const val SOLANA_WEB_CLIENT_CUSTOMIZER = "SOLANA_WEB_CLIENT_CUSTOMIZER"

class SolanaApiClientAutoConfiguration(
    private val applicationEnvironmentInfo: ApplicationEnvironmentInfo
) {

    @Autowired(required = false)
    @Qualifier(SOLANA_WEB_CLIENT_CUSTOMIZER)
    private var webClientCustomizer: WebClientCustomizer = NoopWebClientCustomizer()

    @Bean
    @ConditionalOnMissingBean(SolanaApiServiceUriProvider::class)
    fun unionApiServiceUriProvider(
        @Value("\${rarible.core.client.k8s:true}") k8s: Boolean,
        @Value("\${common.blockchain}") blockchain: String,
    ): SolanaApiServiceUriProvider = if (k8s) {
        K8SSolanaApiServiceUriProvider(blockchain)
    } else {
        SwarmSolanaApiServiceUriProvider(applicationEnvironmentInfo.name)
    }

    @Bean
    @ConditionalOnMissingBean(SolanaNftIndexerApiClientFactory::class)
    fun unionApiClientFactory(
        solanaApiServiceUriProvider: SolanaApiServiceUriProvider,
        @Value("\${rarible.core.client.name:}") clientName: String,
    ): SolanaNftIndexerApiClientFactory {
        val customizers = listOf(DefaultSolanaWebClientCustomizer(clientName), webClientCustomizer)
        val customizer = CompositeWebClientCustomizer(customizers)
        return SolanaNftIndexerApiClientFactory(solanaApiServiceUriProvider, customizer)
    }
}
