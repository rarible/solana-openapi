package com.rarible.solana.protocol.api.client

import com.rarible.solana.protocol.api.ApiClient
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer

class SolanaNftIndexerApiClientFactory(
    private val uriProvider: SolanaApiServiceUriProvider,
    private val webClientCustomizer: WebClientCustomizer
) {

    fun createTokenControllerApiClient(): TokenControllerApi =
        TokenControllerApi(createApiClient())

    fun createBalanceControllerApiClient(): BalanceControllerApi =
        BalanceControllerApi(createApiClient())

    private fun createApiClient(): ApiClient {
        return ApiClient(webClientCustomizer)
            .setBasePath(uriProvider.getUri().toASCIIString())
    }
}
