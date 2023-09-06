package net.averak.cap.infrastructure.mybatis;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

/**
 * Nullableなカラムに@Nullableアノテーションを付与するプラグイン
 */
public class ResolveNullablePlugin extends PluginAdapter {

    @Override
    public boolean validate(final List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
        this.resolveNullable(topLevelClass, introspectedTable);
        return true;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
        this.resolveNullable(topLevelClass, introspectedTable);
        return true;
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
        this.resolveNullable(topLevelClass, introspectedTable);
        return true;
    }

    private void resolveNullable(final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
        final var hasNullableColumn = new AtomicBoolean(false);
        final var hasNonnullColumn = new AtomicBoolean(false);

        IntStream.range(0, topLevelClass.getFields().size()).forEach(i -> {
            final var column = introspectedTable.getAllColumns().get(i);
            if (column.isNullable()) {
                topLevelClass.getFields().get(i).addAnnotation("@Nullable");
                hasNullableColumn.set(true);
            } else if (column.isAutoIncrement()) {
                topLevelClass.getFields().get(i).addAnnotation("@Nullable");
                hasNullableColumn.set(true);
            } else {
                topLevelClass.getFields().get(i).addAnnotation("@Nonnull");
                hasNonnullColumn.set(true);
            }
        });

        if (hasNullableColumn.get()) {
            topLevelClass.addImportedType(new FullyQualifiedJavaType("javax.annotation.Nullable"));
        }
        if (hasNonnullColumn.get()) {
            topLevelClass.addImportedType(new FullyQualifiedJavaType("javax.annotation.Nonnull"));
        }
    }

}
