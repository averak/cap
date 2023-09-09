package net.averak.cap.testutils.db

import groovy.sql.Sql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.sql.DataSource

/**
 * DBテーブルのヘルパー
 */
@Component
class DBUtils {

    private static Sql sql

    @Autowired
    @SuppressWarnings('GrMethodMayBeStatic')
    void setSql(DataSource dataSource) {
        sql = new Sql(dataSource)
    }

    /**
     * レコードを1つ作成
     *
     * @param fixture fixture
     * @return 作成したレコードリスト
     */
    static <T> T insert(final Fixture<T> fixture) {
        sql.dataSet("`$fixture.tableName`").add(fixture.toColumnMap())
        return fixture.entity
    }

    /**
     * レコードを複数作成
     *
     * @param command command
     * @return 作成したレコードリスト
     */
    static <T> List<T> insert(final Fixture<T>... fixtures) {
        return fixtures.collect {
            return insert(it)
        }
    }

}
