package com.rarible.solana.protocol.api

import java.io.InputStream

object SolanaOpenapiReader {

    fun getOpenapi(): InputStream =
        SolanaOpenapiReader::class.java.getResourceAsStream("/solana.yaml")!!

}
