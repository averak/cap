package net.averak.cap.core.utils

import de.huxhorn.sulky.ulid.ULID
import java.util.UUID

class IDUtils {

    companion object {

        private val ulid = ULID()

        fun generateUUIDv4(): String {
            return UUID.randomUUID().toString()
        }

        fun generateULID(): String {
            return ulid.nextULID()
        }

    }
}
