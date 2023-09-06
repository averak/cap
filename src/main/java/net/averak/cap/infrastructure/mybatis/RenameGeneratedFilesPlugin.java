package net.averak.cap.infrastructure.mybatis;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

/**
 * MyBatis Generatorで生成されるファイル名をカスタマイズするプラグイン
 */
public class RenameGeneratedFilesPlugin extends PluginAdapter {

    @Override
    public boolean validate(final List<String> warnings) {
        return true;
    }

    @Override
    public void initialized(final IntrospectedTable introspectedTable) {
        super.initialized(introspectedTable);

        introspectedTable.setBaseRecordType(introspectedTable.getBaseRecordType() + "Entity");
        introspectedTable.setRecordWithBLOBsType(introspectedTable.getRecordWithBLOBsType() + "Entity");

        // 生成されるファイルをXxxMapperからXxxBaseMapperに変更する
        // 生成されたファイルに拡張内容を追記したくないので、XxxBaseMapperを継承したXxxMapperを手動で作成する
        final var javaMapperName = introspectedTable.getMyBatis3JavaMapperType().replaceAll("Mapper$", "BaseMapper");
        introspectedTable.setMyBatis3JavaMapperType(javaMapperName);
        final var xmlMapperName = introspectedTable.getMyBatis3XmlMapperFileName().replaceAll("Mapper\\.xml", "BaseMapper.xml");
        introspectedTable.setMyBatis3XmlMapperFileName(xmlMapperName);
    }

}
