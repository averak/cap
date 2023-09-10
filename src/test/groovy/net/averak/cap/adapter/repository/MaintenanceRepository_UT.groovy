package net.averak.cap.adapter.repository

import net.averak.cap.AbstractDatabaseSpec
import net.averak.cap.adapter.dao.entity.base.MaintenanceEntity
import net.averak.cap.testutils.Faker
import net.averak.cap.testutils.db.DBUtils
import net.averak.cap.testutils.db.Fixture
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDateTime

class MaintenanceRepository_UT extends AbstractDatabaseSpec {

    @Autowired
    MaintenanceRepository sut

    def "find: 開始日時、終了日時からメンテナンス情報を取得"() {
        given:
        DBUtils.insert(
            Fixture.of(MaintenanceEntity, [id: Faker.id("1").value, open_at: LocalDateTime.of(2000, 1, 1, 12, 0), close_at: LocalDateTime.of(2000, 1, 1, 13, 0)]),
            Fixture.of(MaintenanceEntity, [id: Faker.id("2").value, open_at: LocalDateTime.of(2000, 1, 1, 13, 0), close_at: LocalDateTime.of(2000, 1, 1, 14, 0)]),
            Fixture.of(MaintenanceEntity, [id: Faker.id("3").value, open_at: LocalDateTime.of(2000, 1, 1, 14, 0), close_at: LocalDateTime.of(2000, 1, 1, 15, 0)]),
        )

        when:
        final result = this.sut.find(timestamp)

        then:
        result.id == expectedId

        where:
        timestamp                            || expectedId
        LocalDateTime.of(2000, 1, 1, 12, 0)  || Faker.id("1")
        LocalDateTime.of(2000, 1, 1, 12, 59) || Faker.id("1")
        LocalDateTime.of(2000, 1, 1, 13, 0)  || Faker.id("2")
        LocalDateTime.of(2000, 1, 1, 13, 59) || Faker.id("2")
        LocalDateTime.of(2000, 1, 1, 14, 0)  || Faker.id("3")
        LocalDateTime.of(2000, 1, 1, 14, 59) || Faker.id("3")
    }

    def "find: 存在しない場合はNULLを返す"() {
        given:
        DBUtils.insert(Fixture.of(MaintenanceEntity, [open_at: LocalDateTime.of(2000, 1, 1, 12, 0), close_at: LocalDateTime.of(2000, 1, 1, 13, 0)]))

        when:
        final result = this.sut.find(timestamp)

        then:
        result == null

        where:
        timestamp << [
            LocalDateTime.of(2000, 1, 1, 11, 59),
            LocalDateTime.of(2000, 1, 1, 13, 00),
        ]
    }

}