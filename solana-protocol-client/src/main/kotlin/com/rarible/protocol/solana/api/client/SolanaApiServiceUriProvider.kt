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

class K8SSolanaApiServiceUriProvider : SolanaApiServiceUriProvider {
    override fun getUri(): URI = URI.create("http://solana-api:8080")
}
