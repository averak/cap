package net.averak.cap.testutils.db

import com.google.common.base.CaseFormat
import net.averak.cap.testutils.Faker

/**
 * Fixture for database test
 */
class Fixture<T> {

    /**
     * database entity
     */
    T entity

    Fixture(final T entity) {
        this.entity = entity
    }

    /**
     * Fixture を作成
     *
     * デフォルトだと test_helper.random.randomizer.db に定義されているランダマイザーで生成された値がカラムに入る
     * 外部キー制約を持つカラムには適当な値が入っているため、必ず整合性の取れた値を指定すること
     *
     * @param clazz clazz
     * @param additionalDefinitionMap ランダムに生成されたフィールドを上書きする定義マップ
     * @return fixture instance
     */
    static <T> Fixture<T> of(final Class<T> clazz, final Map additionalDefinitionMap = [:]) {
        // MyBatis Generator で生成された Entity クラス以外は指定不可
        if (!clazz.name.matches(".+\\.adapter\\.dao\\.entity\\.base\\.[a-zA-Z]+")) {
            throw new Exception(String.format("Specified generics class `%s` is not database entity.", clazz.simpleName))
        }

        final result = new Fixture<T>(Faker.fake(clazz))
        additionalDefinitionMap.keySet().each { keyName ->
            var fieldName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, keyName.toString())
            fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1)
            final field = clazz.getDeclaredField(fieldName)

            field.accessible = true
            field.set(result.entity, additionalDefinitionMap[keyName])
            field.accessible = false
        }

        return result
    }

    /**
     * テーブル名を取得
     *
     * @return テーブル名
     */
    String getTableName() {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.entity.class.simpleName).replaceAll("_entity", "")
    }

    /**
     * カラムの key-value Map に変換
     *
     * @return column map
     */
    Map toColumnMap() {
        final result = new LinkedHashMap<String, Object>()
        this.entity.class.declaredFields.each {
            final key = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, it.name)
            try {
                result.put(key, this.entity.properties.get(it.name))
            } catch (Exception ignored) {
                throw new Exception(String.format("The class `%s` has no column name `%s`."))
            }

        }
        return result
    }

}
