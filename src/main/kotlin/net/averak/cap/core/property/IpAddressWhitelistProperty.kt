package net.averak.cap.core.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * IP アドレス制限をかけたい API の IP アドレスホワイトリスト
 * IPv4、IPv6、CIDRブロックで指定可能
 */
@Configuration
@ConfigurationProperties("ip-address-whitelist")
open class IpAddressWhitelistProperty {

    lateinit var healthcheckApi: List<String>

}
