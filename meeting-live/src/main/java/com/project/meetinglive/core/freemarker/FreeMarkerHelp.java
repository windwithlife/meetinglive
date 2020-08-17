package com.project.meetinglive.core.freemarker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.project.meetinglive.common.util.FileNameRuleHelp;
import com.project.meetinglive.core.config.ApplicationConfig;
import com.project.meetinglive.core.exception.ServiceException;
import com.project.meetinglive.core.spring.bean.SpringContextHolder;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * freemarket模板工具类
 * @author hejinguo
 * @version $Id: FreeMarkerHelp.java, v 0.1 2017年12月13日 上午11:31:37
 */
public class FreeMarkerHelp {
    private static final Logger  logger = LoggerFactory.getLogger(FreeMarkerHelp.class);
    private static String        fomart = "UTF-8";
    private static Configuration freemarkerConfiguration;

    static {
        if (freemarkerConfiguration == null) {
            synchronized (FreeMarkerHelp.class) {
                if (freemarkerConfiguration == null) {
                    freemarkerConfiguration = SpringContextHolder.getBean(Configuration.class);
                }
            }
        }
    }

    /**
     * 在指定的模板中填充数据后返回模板内容
     * @author hejingou
     * @param map
     * @param ftlName
     * @return
     * @throws TemplateException 
     * @throws IOException 
     * @throws Exception 
     */
    public static String getTemplateHtml(Map<String, Object> map, String ftlName) throws Exception {
        String resultTemplateHtml = "";
        //获取指定的模板
        Template getTemplate = freemarkerConfiguration.getTemplate(ftlName, fomart);
        resultTemplateHtml = FreeMarkerTemplateUtils.processTemplateIntoString(getTemplate, map);
        logger.info("xml文件信息:" + resultTemplateHtml);
        return resultTemplateHtml;
    }

    /**
     * 创建Html文件
     * @param ftlName
     * @param map
     * @return
     */
    public static String makeHtml(String ftlName, Map<String, Object> map) throws Exception {
        //获取当前要生成的HTML页面的文件名称
        String fileTimeName = UUID.randomUUID().toString();
        //静态HTML页面的命名
        String filePostfix = ".html";
        //文件存储目录
        String filePathBase = FileNameRuleHelp.makeTargetPath();
        String filePath = ApplicationConfig.upload_file_dirPath + filePathBase;
        File newsDir = new File(filePath);
        if (!newsDir.exists()) {
            if (!newsDir.mkdirs()) {
                if (logger.isInfoEnabled()) {
                    logger.info("创建静态页面目录异常!");
                }
                throw new ServiceException("生成Html文件创建保存目录失败!");
            }
        }
        //生成Html页面
        String htmlPathUrl = filePath + fileTimeName + filePostfix;
        boolean result = make(ftlName, htmlPathUrl, map);
        if (!result) {
            throw new ServiceException("生成Html文件失败!");
        }
        //文件访问地址
        String fileUrl = ApplicationConfig.upload_image_httpUrl
                         + ApplicationConfig.upload_file_path + filePathBase + fileTimeName
                         + filePostfix;
        return fileUrl;
    }

    /**
     * 创建静态文件
     * @param ftlName
     * @param htmlPath  通过方法  getFreeMarketFoderPath() 获取
     * @param map
     */
    public static boolean make(String ftlName, String htmlPath, Map<String, Object> map) {
        freemarkerConfiguration.setEncoding(Locale.CHINA, fomart);
        boolean result = false;
        try {
            Template getTemplate = freemarkerConfiguration.getTemplate(ftlName, fomart);
            Writer out = new OutputStreamWriter(new FileOutputStream(htmlPath));
            getTemplate.process(map, out);
            out.flush();
            out.close();
            result = true;
        } catch (IOException e) {
            if (logger.isInfoEnabled()) {
                logger.error("加载FTL模板路径错误!", e);
            }
        } catch (TemplateException e) {
            if (logger.isInfoEnabled()) {
                logger.error("FTL模板中加载数据异常!", e);
            }
        } catch (Exception e) {
            logger.error("加载配置文件错误!", e);
        }
        return result;
    }
}
