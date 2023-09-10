package net.averak.cap.core.logger.schema

interface ILogSchema {

    /**
     * 構造化ログに吐き出すキーを取得
     */
    fun fieldKey(): String

}
