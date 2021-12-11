package com.mxt.core.generator;

import com.mxt.core.util.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.ListUtilities;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.List;

public class MapperPlugin extends PluginAdapter {
    
    private static final String DEFAULT_DAO_SUPER_CLASS = "com.mxt.dao.BaseMapper";
    private String daoTargetDir;
    private String daoTargetPackage;
    
    private String daoSuperClass;
    
    // 扩展
    private String expandDaoTargetPackage;
    private String expandDaoSuperClass;
    
    private ShellCallback shellCallback = null;
    
    public MapperPlugin() {
        shellCallback = new DefaultShellCallback(false);
    }
    
    @Override
    public boolean validate(List<String> warnings) {
        daoTargetDir = properties.getProperty("targetProject");
        boolean valid = StringUtils.notBlank(daoTargetDir);
        
        daoTargetPackage = properties.getProperty("targetPackage");
        boolean valid2 = StringUtils.notBlank(daoTargetPackage);
        
        daoSuperClass = properties.getProperty("daoSuperClass");
        if (!StringUtils.notBlank(daoSuperClass)) {
            daoSuperClass = DEFAULT_DAO_SUPER_CLASS;
        }
        
        expandDaoTargetPackage = properties.getProperty("expandTargetPackage");
        expandDaoSuperClass = properties.getProperty("expandDaoSuperClass");
        if (!StringUtils.notBlank(expandDaoSuperClass)) {
            expandDaoSuperClass = DEFAULT_DAO_SUPER_CLASS;
        }
        return valid && valid2;
    }
    
    /**
     * 生成mapping 添加自定义sql
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        
        XmlElement parentElement = document.getRootElement();
        
        //根据非空进行修改
        
        XmlElement answer = new XmlElement("update");
        
        answer.addAttribute(new Attribute(
                "id", introspectedTable.getUpdateByPrimaryKeySelectiveStatementId()));
        
        String parameterType;
        
        if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
            parameterType = introspectedTable.getRecordWithBLOBsType();
        } else {
            parameterType = introspectedTable.getBaseRecordType();
        }
        
        answer.addAttribute(new Attribute("parameterType",
                parameterType));
        
        context.getCommentGenerator().addComment(answer);
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("update ");
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        
        XmlElement dynamicElement = new XmlElement("set");
        answer.addElement(dynamicElement);
        
        for (IntrospectedColumn introspectedColumn : ListUtilities.removeGeneratedAlwaysColumns(introspectedTable
                .getNonPrimaryKeyColumns())) {
            sb.setLength(0);
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(" != null");
            XmlElement isNotNullElement = new XmlElement("if");
            isNotNullElement.addAttribute(new Attribute("test", sb.toString()));
            dynamicElement.addElement(isNotNullElement);
            
            sb.setLength(0);
            sb.append(MyBatis3FormattingUtilities
                    .getEscapedColumnName(introspectedColumn));
            sb.append(" = ");
            sb.append(MyBatis3FormattingUtilities
                    .getParameterClause(introspectedColumn));
            sb.append(',');
            
            isNotNullElement.addElement(new TextElement(sb.toString()));
        }
        
        boolean and = false;
        for (IntrospectedColumn introspectedColumn : introspectedTable
                .getPrimaryKeyColumns()) {
            sb.setLength(0);
            if (and) {
                sb.append("  and ");
            } else {
                sb.append("where ");
                and = true;
            }
            
            sb.append(MyBatis3FormattingUtilities
                    .getEscapedColumnName(introspectedColumn));
            sb.append(" = ");
            sb.append(MyBatis3FormattingUtilities
                    .getParameterClause(introspectedColumn));
            answer.addElement(new TextElement(sb.toString()));
        }
        
        if (context.getPlugins()
                .sqlMapUpdateByPrimaryKeySelectiveElementGenerated(answer,
                        introspectedTable)) {
            parentElement.addElement(answer);
        }
        
        //selectByPara
    
        //创建Select查询
        XmlElement selectByParaXmlElement = new XmlElement("select");
        selectByParaXmlElement.addAttribute(new Attribute("id", "selectByPara"));
        selectByParaXmlElement.addAttribute(new Attribute("resultMap", "BaseResultMap"));
        selectByParaXmlElement.addAttribute(new Attribute("parameterType", "java.lang.String"));
        
        StringBuilder selectByPara = new StringBuilder("select * from ").append(introspectedTable.getFullyQualifiedTableNameAtRuntime()).append(" where 1 = 1");
        
        selectByParaXmlElement.addElement(new TextElement(selectByPara.toString()));
    
        selectByPara.setLength(0);
        selectByPara.append("para != null");
        XmlElement paraElement = new XmlElement("if");
        paraElement.addAttribute(new Attribute("test", selectByPara.toString()));
        selectByParaXmlElement.addElement(paraElement);
        selectByPara.setLength(0);
        selectByPara.append("${para}");
        paraElement.addElement(new TextElement(selectByPara.toString()));
        
        selectByPara.setLength(0);
        selectByPara.append("orderBy != null");
        XmlElement orderByElement = new XmlElement("if");
        orderByElement.addAttribute(new Attribute("test", selectByPara.toString()));
        selectByParaXmlElement.addElement(orderByElement);
        selectByPara.setLength(0);
        selectByPara.append("${orderBy}");
        orderByElement.addElement(new TextElement(selectByPara.toString()));
        
        selectByPara.setLength(0);
        selectByPara.append("page != null");
        XmlElement pageElement = new XmlElement("if");
        pageElement.addAttribute(new Attribute("test", selectByPara.toString()));
        selectByParaXmlElement.addElement(pageElement);
        selectByPara.setLength(0);
        selectByPara.append("${page}");
        pageElement.addElement(new TextElement(selectByPara.toString()));
    
        parentElement.addElement(selectByParaXmlElement);
    
        XmlElement selectCountElement = new XmlElement("select");
        selectCountElement.addAttribute(new Attribute("id", "selectCount"));
        selectCountElement.addAttribute(new Attribute("resultType", "java.lang.Integer"));
    
        //修复生成select count 没有参数的bug
        StringBuilder selectCount = new StringBuilder("select count(*) from ").append(introspectedTable.getFullyQualifiedTableNameAtRuntime()).append(" where 1 = 1");
        selectCountElement.addElement(new TextElement(selectCount.toString()));
        selectCount.setLength(0);
        selectCount.append("para != null");
        XmlElement paraCountElement = new XmlElement("if");
        paraCountElement.addAttribute(new Attribute("test", selectCount.toString()));
        selectCountElement.addElement(paraCountElement);
        selectCount.setLength(0);
        selectCount.append("${para}");
        paraCountElement.addElement(new TextElement(selectCount.toString()));
    
        parentElement.addElement(selectCountElement);
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }
    
}
