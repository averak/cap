package net.averak.cap.domain.primitive.maintenance

import net.averak.cap.AbstractSpec
import net.averak.cap.core.exception.BadRequestException

import java.time.LocalDateTime

import static net.averak.cap.core.exception.BadRequestException.ErrorCode.MAINTENANCE_TIME_IS_INVALID

class MaintenanceTime_UT extends AbstractSpec {

    def "MaintenanceTime: 正常に作成できる"() {
        when:
        new MaintenanceTime(openAt, closeAt)

        then:
        noExceptionThrown()

        where:
        openAt                                | closeAt
        LocalDateTime.of(2000, 1, 1, 0, 0, 0) | LocalDateTime.of(2000, 1, 1, 0, 0, 1)
    }

    def "MaintenanceTime: 制約違反の場合は400エラー"() {
        when:
        new MaintenanceTime(openAt, closeAt)

        then:
        final exception = thrown(BadRequestException)
        exception.errorCode == MAINTENANCE_TIME_IS_INVALID

        where:
        openAt                                | closeAt
        LocalDateTime.of(2000, 1, 1, 0, 0, 0) | LocalDateTime.of(2000, 1, 1, 0, 0, 0)
        LocalDateTime.of(2000, 1, 1, 0, 0, 1) | LocalDateTime.of(2000, 1, 1, 0, 0, 0)
    }

    def "contains: タイムスタンプを含むか判定"() {
        given:
        final time = new MaintenanceTime(openAt, closeAt)

        when:
        final result = time.contains(LocalDateTime.of(2000, 1, 1, 12, 0))

        then:
        result == expectedResult

        where:
        openAt                              | closeAt                              || expectedResult
        LocalDateTime.of(2000, 1, 1, 12, 0) | LocalDateTime.of(2000, 1, 1, 13, 0)  || true
        LocalDateTime.of(2000, 1, 1, 11, 0) | LocalDateTime.of(2000, 1, 1, 11, 59) || false
        LocalDateTime.of(2000, 1, 1, 12, 1) | LocalDateTime.of(2000, 1, 1, 13, 0)  || false
        LocalDateTime.of(2000, 1, 1, 11, 0) | LocalDateTime.of(2000, 1, 1, 12, 0)  || false
    }

}