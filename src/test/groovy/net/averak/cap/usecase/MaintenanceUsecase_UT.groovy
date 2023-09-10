package net.averak.cap.usecase

import net.averak.cap.domain.model.Maintenance
import net.averak.cap.testutils.Faker
import org.springframework.beans.factory.annotation.Autowired

class MaintenanceUsecase_UT extends AbstractUsecase_UT {

    @Autowired
    MaintenanceUsecase sut

    def "getCurrentMaintenance: 正常系 現在のメンテナンス情報を取得できる"() {
        given:
        final maintenance = Faker.fake(Maintenance)

        when:
        final result = this.sut.getCurrentMaintenance()

        then:
        1 * this.maintenanceRepository.find(_) >> maintenance
        result == maintenance
    }

}