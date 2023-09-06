package net.averak.cap.adapter.handler.controller

import org.springframework.http.HttpStatus

class HealthCheckController_IT extends AbstractController_IT {

    // API PATH
    static final String BASE_PATH = "/api/health"
    static final String HEALTH_CHECK_PATH = BASE_PATH

    def "ヘルスチェックAPI: 正常系 200 OKを返す"() {
        when:
        final request = this.getRequest(HEALTH_CHECK_PATH)
        this.execute(request, HttpStatus.OK)

        then:
        final createdEcho = sql.firstRow("SELECT * FROM echo")
        createdEcho.message == "health check"
    }

}