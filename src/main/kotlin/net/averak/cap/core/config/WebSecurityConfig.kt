package net.averak.cap.core.config

import net.averak.cap.core.exception.UnauthorizedException
import net.averak.cap.core.exception.UnauthorizedException.ErrorCode.NOT_LOGGED_IN
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.servlet.HandlerExceptionResolver

@Configuration
@EnableWebSecurity
open class WebSecurityConfig(
    private val handlerExceptionResolver: HandlerExceptionResolver,
) {

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

        // 認証認可の例外ハンドラ
        http.exceptionHandling { httpSecurityExceptionHandlingConfigurer ->
            httpSecurityExceptionHandlingConfigurer
                // 未認証のユーザに適応されるハンドラ
                .authenticationEntryPoint { request, response, _ ->
                    this.handlerExceptionResolver.resolveException(
                        request,
                        response,
                        null,
                        UnauthorizedException(NOT_LOGGED_IN)
                    )
                }
                // 認証済みだが権限を持たないユーザに適応されるハンドラ
                // RBAC は実装するとしても Spring Security の外側で独自に行うので、ここでは 401 Unauthorized を返却する
                .accessDeniedHandler { request, response, _ ->
                    this.handlerExceptionResolver.resolveException(
                        request,
                        response,
                        null,
                        UnauthorizedException(NOT_LOGGED_IN)
                    )
                }
        }

        return http.build()
    }

}
