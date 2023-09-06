package net.averak.cap.core.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
open class WebSecurityConfig {

    @Bean
    open fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        // CORSを無効化
        http.cors(CorsConfigurer<HttpSecurity>::disable)

        // CSRF対策を有効化
        http.csrf(Customizer.withDefaults())

        // アクセス許可
        http.authorizeHttpRequests { authorizationManagerRequestMatcherRegistry ->
            authorizationManagerRequestMatcherRegistry
                .requestMatchers("/api/health").permitAll()
                .requestMatchers("/api/**").permitAll()
                // 静的コンテンツ
                .requestMatchers("/v3/api-docs/**", "/swagger-resources/**", "/swagger-ui/**", "/webjars/**")
                .permitAll() //
                .requestMatchers("/**", "**.**").permitAll() //
                .anyRequest().authenticated()
        }

        return http.build()
    }

}
