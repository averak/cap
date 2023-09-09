package net.averak.cap.adapter.handler

import net.averak.cap.core.exception.NotFoundException

import static net.averak.cap.core.exception.NotFoundException.ErrorCode.NOT_FOUND_API

class GlobalControllerAdvice_IT extends AbstractController_IT {

    def "異常系 存在しないパスにリクエストすると404を返す"() {
        given:
        final path = "/api/xxx"

        when: "GET"
        final getRequest = this.getRequest(path)

        then:
        execute(getRequest, new NotFoundException(NOT_FOUND_API))

        when: "POST"
        final postRequest = this.postRequest(path)

        then:
        execute(postRequest, new NotFoundException(NOT_FOUND_API))

        when: "PUT"
        final putRequest = this.putRequest(path, null)

        then:
        execute(putRequest, new NotFoundException(NOT_FOUND_API))

        when: "DELETE"
        final deleteRequest = this.deleteRequest(path)

        then:
        execute(deleteRequest, new NotFoundException(NOT_FOUND_API))
    }

}