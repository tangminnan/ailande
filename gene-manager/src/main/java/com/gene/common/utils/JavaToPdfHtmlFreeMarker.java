package com.gene.common.utils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;


import freemarker.template.Configuration;
import freemarker.template.Template;
/**
 * html转换为pdf导出工具类
 */
public class JavaToPdfHtmlFreeMarker {
	private static final String DEST = "target/HelloWorld_CN_HTML_FREEMARKER.pdf";
    private static final String HTML = "/baogao-3.html";
    private static final String WINDOWS_FONT = "D:/Documents/Downloads/simsun.ttc";
    private static final String LINUX_FONT="/usr/share/fonts/chiness/simsun.ttc";
    private static Map<String,Object> paramsMap = new HashMap<String,Object>();
    public JavaToPdfHtmlFreeMarker(Map<String,Object> paramsMap){
    	this.paramsMap=paramsMap;
    }
  
    private static Configuration freemarkerCfg = null;
  
    static {
        freemarkerCfg =new Configuration();
       //freemarker的模板目录
 //       try {
        //    freemarkerCfg.setDirectoryForTemplateLoading(new File(PathUtil.getCurrentPath()));
        	freemarkerCfg.setDefaultEncoding("utf-8");   
            freemarkerCfg.setClassForTemplateLoading(JavaToPdfHtmlFreeMarker.class, "/");
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    }
  
  
    /*public static void main(String[] args) throws IOException, DocumentException {
        String content = JavaToPdfHtmlFreeMarker.freeMarkerRender(HTML);
        JavaToPdfHtmlFreeMarker.createPdf(content,DEST);
    }*/
    
    public void exportPDF(){
    	  String content = JavaToPdfHtmlFreeMarker.freeMarkerRender(HTML);
          try {
			JavaToPdfHtmlFreeMarker.createPdf(content,DEST);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
    }
  
  
    public static void createPdf(String content,String dest) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        if ("linux".equals(getCurrentOperationSystem())) {
        	fontImp.register(LINUX_FONT);
        }else{
        	fontImp.register(WINDOWS_FONT);
        }
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new ByteArrayInputStream(content.getBytes()), null, Charset.forName("UTF-8"), fontImp);
        // step 5
        document.close();
  
    }
  
    /**
     * freemarker渲染html
     */
    public static String freeMarkerRender(String htmlTmp) {
        Writer out = new StringWriter();
        try {
            // 获取模板,并设置编码方式
            Template template = freemarkerCfg.getTemplate(htmlTmp);
            template.setEncoding("UTF-8");
            // 合并数据模型与模板
            template.process(paramsMap, out); //将合并后的数据和模板写入到流中，这里使用的字符流
            out.flush();
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    private static String getCurrentOperationSystem() {
        String os = System.getProperty("os.name").toLowerCase();
        return os;
    }
}


