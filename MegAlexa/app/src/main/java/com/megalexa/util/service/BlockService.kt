package com.megalexa.util.service


abstract class BlockService : Service() {

    override val resource: String
        get() = "block"

}