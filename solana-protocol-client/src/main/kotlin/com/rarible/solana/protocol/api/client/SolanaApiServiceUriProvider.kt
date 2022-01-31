package com.rarible.solana.protocol.api.client

import java.net.URI

interface SolanaApiServiceUriProvider {
    fun getUri(): URI
}

class FixedSolanaApiServiceUriProvider(
    private val fixedURI: URI
) : SolanaApiServiceUriProvider {
    override fun getUri(): URI = fixedURI
}

class SwarmSolanaApiServiceUriProvider(
    private val environment: String
) : SolanaApiServiceUriProvider {
    override fun getUri(): URI = URI.create(String.format("http://%s-solana-protocol-api:8080", environment))
}
