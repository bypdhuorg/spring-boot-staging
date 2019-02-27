package com.bianbian.generator;

import com.bianbian.generator.bean.EntityOfEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 代码生成器
 */
@Slf4j
public class XbootGenerator {

    private static final String module="base";
    /**
     * 实体类名
     * 建议仅需修改
     */
    private static final String className = "TestR3";

    /**
     * 类说明描述
     * 建议仅需修改
     */
    private static final String description = "用户2";

    /**
     * 作者名
     * 建议仅需修改
     */
    private static final String author = "God";

    /**
     * 数据库表名前缀
     * 下方请根据需要修改
     */
    private static final String tablePre = "bt_";

    /**
     * 主键类型
     */
    private static final String primaryKeyType = "String";

    /**
     * 实体类对应包
     * (文件自动生成至该包下)
     */
    private static final String entityPackage = "com.bianbian.modules."+module+".entity";

    /**
     * dao对应包
     * (文件自动生成至该包下)
     */
    private static final String daoPackage = "com.bianbian.modules."+module+".dao";

    /**
     * service对应包
     * (文件自动生成至该包下)
     */
    private static final String servicePackage = "com.bianbian.modules."+module+".service";

    /**
     * serviceImpl对应包
     * (文件自动生成至该包下)
     */
    private static final String serviceImplPackage = "com.bianbian.modules."+module+".service.impl";

    /**
     * controller对应包
     * (文件自动生成至该包下)
     */
    private static final String controllerPackage = "com.bianbian.modules."+module+".controller";

    /**
     * 运行该主函数即可生成代码
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        //模板路径
        String root = System.getProperty("user.dir")+"/src/main/java/com/bianbian/generator/template";
        FileResourceLoader resourceLoader = new FileResourceLoader(root,"utf-8");
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);

        //生成代码
        generateCode(gt);

        //根据类名删除生成的代码
        //deleteCode(className);
    }

    /**
     * 生成代码
     * @param gt
     * @throws IOException
     */
    private static void generateCode(GroupTemplate gt) throws IOException{

        Template entityTemplate = gt.getTemplate("entity.btl");
        Template daoTemplate = gt.getTemplate("dao.btl");
        Template serviceTemplate = gt.getTemplate("service.btl");
        Template serviceImplTemplate = gt.getTemplate("serviceImpl.btl");
        Template controllerTemplate = gt.getTemplate("controller.btl");
        Template controllerTestTemplate = gt.getTemplate("controllertest.btl");

        EntityOfEntity entity = new EntityOfEntity();
        entity.setEntityPackage(entityPackage);
        entity.setDaoPackage(daoPackage);
        entity.setServicePackage(servicePackage);
        entity.setServiceImplPackage(serviceImplPackage);
        entity.setControllerPackage(controllerPackage);
        entity.setAuthor(author);
        entity.setClassName(className);
        entity.setTableName(tablePre+camel2Underline(className));
        entity.setClassNameLowerCase(first2LowerCase(className));
        entity.setDescription(description);
        entity.setPrimaryKeyType(primaryKeyType);

        OutputStream out = null;

        //生成实体类代码
        entityTemplate.binding("entity",entity);
        String entityResult = entityTemplate.render();
        log.info(entityResult);
        //创建文件
        String entityFileUrl = System.getProperty("user.dir")+"/src/main/java/"+ dotToLine(entityPackage) + "/" + className + ".java";
        File entityFile = new File(entityFileUrl);
        File entityDir = entityFile.getParentFile();
        if (!entityDir.exists()) {
            entityDir.mkdirs();
        }
        if(!entityFile.exists()){
            //实体类若存在则不重新生成
            entityFile.createNewFile();
            out = new FileOutputStream(entityFile);
            entityTemplate.renderTo(out);
        }

        //生成dao代码
        daoTemplate.binding("entity",entity);
        String daoResult = daoTemplate.render();
        log.info(daoResult);
        //创建文件
        String daoFileUrl = System.getProperty("user.dir")+"/src/main/java/"+ dotToLine(daoPackage) + "/" +className + "Dao.java";
        File daoFile = new File(daoFileUrl);
        File daoDir = daoFile.getParentFile();
        if (!daoDir.exists()) {
            daoDir.mkdirs();
        }
        daoFile.createNewFile();
        out = new FileOutputStream(daoFile);
        daoTemplate.renderTo(out);

        //生成service代码
        serviceTemplate.binding("entity",entity);
        String serviceResult = serviceTemplate.render();
        log.info(serviceResult);
        //创建文件
        String serviceFileUrl = System.getProperty("user.dir")+"/src/main/java/"+ dotToLine(servicePackage) + "/" + className + "Service.java";
        File serviceFile = new File(serviceFileUrl);
        File serviceDir = serviceFile.getParentFile();
        if (!serviceDir.exists()) {
            serviceDir.mkdirs();
        }
        serviceFile.createNewFile();
        out = new FileOutputStream(serviceFile);
        serviceTemplate.renderTo(out);

        //生成serviceImpl代码
        serviceImplTemplate.binding("entity",entity);
        String serviceImplResult = serviceImplTemplate.render();
        log.info(serviceImplResult);
        //创建文件
        String serviceImplFileUrl = System.getProperty("user.dir")+"/src/main/java/"+ dotToLine(serviceImplPackage) + "/" + className + "ServiceImpl.java";
        File serviceImplFile = new File(serviceImplFileUrl);
        File serviceImplDir = serviceImplFile.getParentFile();
        if (!serviceImplDir.exists()) {
            serviceImplDir.mkdirs();
        }
        serviceImplFile.createNewFile();
        out = new FileOutputStream(serviceImplFile);
        serviceImplTemplate.renderTo(out);

        //生成controller代码
        controllerTemplate.binding("entity",entity);
        String controllerResult = controllerTemplate.render();
        log.info(controllerResult);
        //创建文件
        String controllerFileUrl = System.getProperty("user.dir")+"/src/main/java/"+ dotToLine(controllerPackage) + "/" + className + "Controller.java";
        File controllerFile = new File(controllerFileUrl);
        File controllerDir = controllerFile.getParentFile();
        if (!controllerDir.exists()) {
            controllerDir.mkdirs();
        }
        controllerFile.createNewFile();
        out = new FileOutputStream(controllerFile);
        controllerTemplate.renderTo(out);

        //生成controller Test代码
        controllerTestTemplate.binding("entity",entity);
        //创建文件
        String controllerTestFileUrl = System.getProperty("user.dir")+"/src/test/java/"+ dotToLine(controllerPackage) + "/" + className + "ControllerTest.java";
        File controllerTestFile = new File(controllerTestFileUrl);
        File controllerTestDir = controllerTestFile.getParentFile();
        if (!controllerTestDir.exists()) {
            controllerTestDir.mkdirs();
        }
        controllerTestDir.createNewFile();
        out = new FileOutputStream(controllerTestFile);
        controllerTestTemplate.renderTo(out);

        out.close();
        log.info("生成代码成功！");
    }

    /**
     * 删除指定类代码
     * @param className
     * @throws IOException
     */
    private static void deleteCode(String className) throws IOException{

        String entityFileUrl = System.getProperty("user.dir")+"/src/main/java/"+ dotToLine(entityPackage) + "/" +className+".java";
        File entityFile = new File(entityFileUrl);
        if(entityFile.exists()){
            entityFile.delete();
        }
        String daoFileUrl = System.getProperty("user.dir")+"/src/main/java/"+ dotToLine(daoPackage) + "/" +className+"Dao.java";
        File daoFile = new File(daoFileUrl);
        if(daoFile.exists()){
            daoFile.delete();
        }

        String serviceFileUrl = System.getProperty("user.dir")+"/src/main/java/"+ dotToLine(servicePackage) + "/" +className+"Service.java";
        File serviceFile = new File(serviceFileUrl);
        if(serviceFile.exists()){
            serviceFile.delete();
        }

        String serviceImplFileUrl = System.getProperty("user.dir")+"/src/main/java/"+ dotToLine(serviceImplPackage) + "/" +className+"ServiceImpl.java";
        File serviceImplFile = new File(serviceImplFileUrl);
        if(serviceImplFile.exists()){
            serviceImplFile.delete();
        }

        String controllerFileUrl = System.getProperty("user.dir")+"/src/main/java/"+ dotToLine(controllerPackage) + "/" +className+"Controller.java";
        File controllerFile = new File(controllerFileUrl);
        if(controllerFile.exists()){
            controllerFile.delete();
        }

        log.info("删除代码完毕！");
    }

    /**
     * 点转斜线
     * @param str
     * @return
     */
    public static String dotToLine(String str){
        return str.replace(".", "/");
    }

    /**
     * 驼峰法转下划线
     */
    public static String camel2Underline(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        if(str.length()==1){
            return str.toLowerCase();
        }
        StringBuffer sb = new StringBuffer();
        for(int i=1;i<str.length();i++){
            if(Character.isUpperCase(str.charAt(i))){
                sb.append("_"+Character.toLowerCase(str.charAt(i)));
            }else{
                sb.append(str.charAt(i));
            }
        }
        return (str.charAt(0)+sb.toString()).toLowerCase();
    }

    /**
     * 首字母小写
     */
    public static String first2LowerCase(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        if(str.length()==1){
            return str.toLowerCase();
        }
        StringBuffer sb = new StringBuffer();
        sb.append(Character.toLowerCase(str.charAt(0)));
        sb.append(str.substring(1,str.length()));
        return sb.toString();
    }
}
