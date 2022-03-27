package com.rarible.protocol.solana.api.client

import com.rarible.protocol.solana.api.ApiClient
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer

class SolanaNftIndexerApiClientFactory(
    private val uriProvider: SolanaApiServiceUriProvider,
    private val webClientCustomizer: WebClientCustomizer
) {

    fun createTokenControllerApiClient(): TokenControllerApi =
        TokenControllerApi(createApiClient())

    fun createBalanceControllerApiClient(): BalanceControllerApi =
        BalanceControllerApi(createApiClient())

    fun createCollectionControllerApiClient(): CollectionControllerApi =
        CollectionControllerApi(createApiClient())

    fun createOrderControllerApiClient(): OrderControllerApi =
        OrderControllerApi(createApiClient())

    fun createActivityControllerApiClient(): ActivityControllerApi =
        ActivityControllerApi(createApiClient())

    fun createSignControllerApiClient(): SignatureControllerApi =
        SignatureControllerApi(createApiClient())

    private fun createApiClient(): ApiClient {
        return ApiClient(webClientCustomizer)
            .setBasePath(uriProvider.getUri().toASCIIString())
    }
}
