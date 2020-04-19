package com.gene.common.utils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;


import freemarker.template.Configuration;
import freemarker.template.Template;
import sun.misc.BASE64Encoder;
/**
 * html转换为pdf导出工具类
 */
public class JavaToPdfHtmlFreeMarker {
    private static final String HTML = "/baogao-3.html";
    private static final String WINDOWS_FONT = "D:/Documents/Downloads/simsun.ttf";
    private static final String LINUX_FONT="/usr/share/fonts/chiness/simsun.ttc";
    private static final String CSS_RESOURSE="/css.css";
    public static final String PDF_PATH = "D:/xx.pdf";

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
    
    public void exportPDF(HttpServletResponse response){
    	  String content = JavaToPdfHtmlFreeMarker.freeMarkerRender(HTML);
          try {
			JavaToPdfHtmlFreeMarker.createPdf(response,content);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
    }
  
  
    public static void createPdf(HttpServletResponse response, String content) throws IOException, DocumentException {
    	System.out.println(content);
        Document document = new Document(PageSize.A4, 30, 30, 30, 30);
        document.setMargins(30, 30, 30, 30);
        String fileName = URLEncoder.encode("艾兰德打印文件.pdf", "UTF-8");
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/pdf");
        OutputStream outputStream= response.getOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document,outputStream);
        document.open();
        String cssSource = getURLSource(new File("src/main/resources/"+CSS_RESOURSE));
        InputStream cssis = new ByteArrayInputStream(cssSource.toString().getBytes());
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(content.getBytes("UTF-8")),null,Charset.forName("UTF-8"),new AsianFontProvider());
        document.close();
        outputStream.close();
    }
    
    public static void createPdf2(HttpServletResponse response, String content) throws IOException, DocumentException {
    	OutputStream os = null;
        try {
            Long startTime = System.currentTimeMillis();

            os = new FileOutputStream(PDF_PATH);
            ITextRenderer renderer = new ITextRenderer();
            ITextFontResolver fontResolver = renderer.getFontResolver();
            // 设置中文字体/宋体
            fontResolver.addFont(WINDOWS_FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            // 获取HTML文件的URL
        //    String url = new File(HTML).toURI().toURL().toString();
            // 方式一/URL方式生成PDF
       //    renderer.setDocument(url);
            // 方式二/HTML代码字符串方式生成PDF
            // HTML代码字符串
            String htmlString = "<html></html>";
            renderer.setDocumentFromString(content);
            renderer.layout();
            renderer.createPDF(os);
            Long endTime = System.currentTimeMillis();
            System.out.print("Itext parse Html to Pdf End -> " + (endTime -startTime));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           if (null != os) {
               try {
                   os.close();
                   
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        }
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
    /**
     * 中文字体
     */
    static class AsianFontProvider extends XMLWorkerFontProvider {
    	 
        @Override
        public Font getFont(final String fontname, String encoding, float size, final int style) {
            String fntname = fontname;
            if (fntname == null) {
               /*使用的windows里的宋体，可将其文件放资源文件中引入
                *请确保simsun.ttc字体在windows下支持
                *我是将simsun.ttc字体打进maven的jar包中使用
                */
            	if ("linux".equals(getCurrentOperationSystem())) 
            		fntname = LINUX_FONT;
            	else
            		fntname=WINDOWS_FONT;
            }
            if (size == 0) {
                size = 4;
            }
            return super.getFont(fntname, encoding, size, style);
        }
    }
    
    /**
     * css
     */
    public static String getURLSource(File url)
    {
    	InputStream inStream=null;
    	String htmlSource=null;
		try {
			inStream = new FileInputStream(url);
			// 通过输入流获取html二进制数据
	    	byte [] data = readInputStream(inStream); // 把二进制数据转化为byte字节数据
	    	htmlSource = new String(data);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	try {
			inStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return htmlSource;
    }
    
    
    /**
     * 把二进制流转化为byte字节数组
     */
    public static byte [] readInputStream(InputStream instream)
    {
    	ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    	byte [] buffer = new byte[1204];
    	int len = 0;
    	try {
			while ((len = instream.read(buffer)) != -1)
			{
				outStream.write(buffer, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			instream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return outStream.toByteArray();
    }
    
    public static String getImageBase64String(File imgFile) throws IOException {
        InputStream inputStream = new FileInputStream(imgFile);
        byte[] data = new byte[inputStream.available()];
        int totalNumberBytes = inputStream.read(data);
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
}


