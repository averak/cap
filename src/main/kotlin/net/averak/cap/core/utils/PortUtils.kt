package net.averak.cap.core.utils

import java.io.IOException
import java.net.Socket

class PortUtils {

    companion object {

        fun isUnused(port: Int): Boolean {
            try {
                Socket("localhost", port).use {
                    // Socketが正常に作成できれば、ポートは使用中
                    return false
                }
            } catch (e: IOException) {
                // IOExceptionが発生した場合、ポートは使用可能
                return true
            }

        }

    }

}
