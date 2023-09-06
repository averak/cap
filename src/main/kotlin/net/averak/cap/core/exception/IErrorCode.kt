package net.averak.cap.core.exception

interface IErrorCode {

    /**
     * エラーコード名
     */
    val name: String

    /**
     * resources/i18n/message.yml で定義されるキー
     */
    val messageSourceKey: String

}
