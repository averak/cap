package net.averak.cap.infrastructure.mybatis;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * MyBatis Generatorで不要なテーブルを無視するプラグイン
 */
public class IgnoreTablePlugin extends PluginAdapter {

    private final List<String> IGNORE_TABLES = List.of( //
        "flyway_schema_history", //
        "oauth2_authorized_client", //
        "SPRING_SESSION", //
        "SPRING_SESSION_ATTRIBUTES" //
    );

    @Override
    public boolean validate(final List<String> warnings) {
        return true;
    }

    private boolean checkIsTableToGenerate(final IntrospectedTable introspectedTable) {
        final var tableName = introspectedTable.getFullyQualifiedTableNameAtRuntime().replace("`", "");
        return this.IGNORE_TABLES.stream().noneMatch(tableName::equals);
    }

    @Override
    public boolean modelBaseRecordClassGenerated(final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
        return this.checkIsTableToGenerate(introspectedTable);
    }

    @Override
    public boolean modelExampleClassGenerated(final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
        return this.checkIsTableToGenerate(introspectedTable);
    }

    @Override
    public boolean clientGenerated(final Interface interfaze, final IntrospectedTable introspectedTable) {
        return this.checkIsTableToGenerate(introspectedTable);
    }

    @Override
    public boolean sqlMapGenerated(final GeneratedXmlFile sqlMap, final IntrospectedTable introspectedTable) {
        return this.checkIsTableToGenerate(introspectedTable);
    }

}
