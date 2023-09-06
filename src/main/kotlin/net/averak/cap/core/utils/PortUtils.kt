package net.averak.cap.core.utils

import java.io.IOException
import java.net.Socket

class PortUtils {

    companion object {

        fun findUnusedPorts(): List<Int> {
            // 別プロジェクトに割り当てられているが、たまたまポートが解放されているだけの可能性もあるので複数ポートを返却する必要がある
            var counter = 0
            val unusedPorts = mutableListOf<Int>()
            for (port in 50000..65535) {
                // 割り当て可能な全ポートをチェックするのは無駄なので、100件程度取得すれば十分
                if (counter >= 100) {
                    break
                }
                if (isUnused(port)) {
                    counter++
                    unusedPorts.add(port)
                }
            }

            return unusedPorts
        }

        private fun isUnused(port: Int): Boolean {
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
