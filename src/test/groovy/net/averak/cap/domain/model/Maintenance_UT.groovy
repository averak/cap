package net.averak.cap.domain.model

import net.averak.cap.AbstractSpec
import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.domain.primitive.maintenance.MaintenanceMemo
import net.averak.cap.domain.primitive.maintenance.MaintenanceTime
import net.averak.cap.testutils.Faker

import java.time.LocalDateTime

class Maintenance_UT extends AbstractSpec {

    def "isEnabled: 指定されたタイムスタンプ時点で有効なメンテナンスか判定"() {
        given:
        final time = new MaintenanceTime(openAt, closeAt)
        final maintenance = new Maintenance(Faker.fake(ID), time, Faker.fake(MaintenanceMemo))

        when:
        final result = maintenance.isEnabled(LocalDateTime.of(2000, 1, 1, 12, 0))

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