package net.averak.cap.testutils.randomizer.domain.primitive.common

import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.testutils.randomizer.IRandomizer
import org.springframework.stereotype.Component

@Component
class IDRandomizer implements IRandomizer {

    final Class targetType = ID.class

    private static counter = 0

    @Override
    Object getRandomValue() {
        // 先頭10桁のタイムスタンプ部はそのまま残し、残り16桁のランダム部をシーケンシャルな文字列で置換する
        final ulid = (new ID()).value
        return new ID(ulid.take(10) + String.format("%016d", ++counter))
    }

}
