/*
 * Copyright 2019 Web3 Labs Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.web3j.aion.protocol

import assertk.assertThat
import assertk.assertions.isEqualTo
import erc20.ERC20Token
import helloavm.HelloAvm
import org.junit.jupiter.api.Test
import org.web3j.aion.VirtualMachine
import org.web3j.aion.tx.gas.AionGasProvider

class AvmIntegrationTest : AionIntegrationTest(VirtualMachine.AVM) {

    @Test
    internal fun `deploy and call HelloAvm contract`() {
        HelloAvm.deploy(aion, manager, AionGasProvider).send().apply {
            assertThat(call_getString().send()).isEqualTo("Hello AVM")

            send_setString("Hello test").send()
            assertThat(call_getString().send()).isEqualTo("Hello test")
        }
    }

    @Test
    internal fun `deploy and call ERC20Token contract`() {
        ERC20Token.deploy(
            aion, manager, AionGasProvider,
            "Test", "TEST", 2, keyPair.address
        ).send().apply {
            assertThat(call_name().send()).isEqualTo("Test")
            assertThat(call_symbol().send()).isEqualTo("TEST")
            assertThat(call_decimals().send()).isEqualTo(2)
        }
    }
}
