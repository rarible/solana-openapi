package com.rarible.protocol.solana.api

import java.io.InputStream

object SolanaOpenapiReader {

    fun getOpenapi(): InputStream =
        SolanaOpenapiReader::class.java.getResourceAsStream("/solana.yaml")!!

}
