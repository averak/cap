package net.averak.cap.testutils.randomizer

import org.jeasy.random.api.Randomizer

/**
 * ランダムオブジェクトを生成するジェネレータ定義
 * プリミティブ型のフィールドに、ドメイン制約に準拠したオブジェクトを生成したい場合に Bean 定義すること
 */
interface IRandomizer extends Randomizer {

    Object getRandomValue()

    Class getTargetType()

}
