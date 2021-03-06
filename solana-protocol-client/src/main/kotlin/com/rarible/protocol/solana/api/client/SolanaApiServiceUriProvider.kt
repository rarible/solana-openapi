package com.rarible.protocol.solana.api.client

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
    override fun getUri(): URI = URI.create(String.format("http://%s-solana-api:8080", environment))
}

class K8SSolanaApiServiceUriProvider : SolanaApiServiceUriProvider {
    override fun getUri(): URI = URI.create("http://solana-api:8080")
}
