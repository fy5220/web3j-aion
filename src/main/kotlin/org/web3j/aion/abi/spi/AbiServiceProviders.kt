package org.web3j.aion.abi.spi

import org.web3j.abi.FunctionEncoder
import org.web3j.abi.FunctionReturnDecoder
import org.web3j.abi.spi.FunctionDecoderProvider
import org.web3j.abi.spi.FunctionEncoderProvider
import org.web3j.aion.abi.AbiFunctionEncoder
import org.web3j.aion.abi.AionFunctionReturnDecoder

class AbiFunctionEncoderProvider : FunctionEncoderProvider {
    override fun get(): FunctionEncoder = AbiFunctionEncoder
}

class AbiFunctionDecoderProvider : FunctionDecoderProvider {
    override fun get(): FunctionReturnDecoder = AionFunctionReturnDecoder
}
