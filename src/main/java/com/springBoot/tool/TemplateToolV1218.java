package com.springBoot.tool;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class TemplateToolV1218 {
	private static Map<String, Template> templateMap = new HashMap<String, Template>();
	
	public static String fill(VelocityContext context, String tmdir,String tmFile) {
		String result = "";
		Template t = null;
		StringWriter writer = new StringWriter();
		try {
			if (!templateMap.containsKey(tmFile)) {
				Properties p = new Properties();
				p.setProperty("file.resource.loader.path", tmdir);
				VelocityEngine ve = new VelocityEngine();
				ve.setProperty("runtime.log.logsystem.class","org.apache.velocity.runtime.log.NullLogChute");
				ve.init(p);
				t = ve.getTemplate(tmFile);
				templateMap.put(tmFile, t);
			}else {
				t = templateMap.get(tmFile);
			}
			System.out.println("=============================================================");
			System.out.println("templateMap.size():"+templateMap.size());
			
			t.merge(context, writer);
			result = writer.toString();
			writer.close();
		} catch (Exception e) {
			LogTool.error(TemplateToolV1218.class, e);
		} finally{
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					LogTool.error(TemplateToolV1218.class, e);
				}
			}
		}
		return result;
	}

	public static String fill(VelocityContext context, String tmdir,String tmFile, String inputEncode, String ouputEncode) {
		String result = "";
		Template t = null;
		StringWriter writer = new StringWriter();
		try {
			if (!templateMap.containsKey(tmFile)) {
				Properties p = new Properties();
				p.setProperty("file.resource.loader.path", tmdir);
				p.setProperty("ISO-8859-1", "UTF-8");
				p.setProperty("input.encoding", inputEncode);
				p.setProperty("output.encoding", ouputEncode);
				VelocityEngine ve = new VelocityEngine();
				ve.setProperty("runtime.log.logsystem.class","org.apache.velocity.runtime.log.NullLogChute");
				ve.init(p);
				t = ve.getTemplate(tmFile);
				templateMap.put(tmFile, t);
			}else {
				t = templateMap.get(tmFile);
			}
			
			System.out.println("=============================================================");
			System.out.println("templateMap.size():"+templateMap.size());
			t.merge(context, writer);
			result = writer.toString();
			writer.close();
		} catch (Exception e) {
			LogTool.error(TemplateToolV1218.class, e);
		} finally{
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					LogTool.error(TemplateToolV1218.class, e);
				}
			}
		}
		return result;
	}

	public static String fill(VelocityContext context, String templateString) {
		String result = "";
		try {
			VelocityEngine ve = new VelocityEngine();
			ve.init();
			StringWriter writer = new StringWriter();
			StringReader reader = new StringReader(templateString);
			ve.evaluate(context, writer, "temp", reader);
			result = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String test(int i) {
		return "array:" + i;
	}

	public static void main(String[] args) {
//		String tmdir = TemplateToolV1218.class.getResource("/template/test/").getPath();

//		String inputEncode = "GBK";
//		String ouputEncode = "GBK";
		String result = "";
		try {
//			// 初始化模板引擎
//			Properties p = new Properties();
//			// 配置引擎上下文对象
//			VelocityContext context = new VelocityContext();
//			// 加载模板文件
//			// context.put("selects", "");
//			p.setProperty("file.resource.loader.path", path);
//			p.setProperty("ISO-8859-1", "UTF-8");
//			p.setProperty(Velocity.FILE_RESOURCE_LOADER_CACHE, "true");
//			p.setProperty(Velocity.INPUT_ENCODING, inputEncode);
//			p.setProperty(Velocity.OUTPUT_ENCODING, ouputEncode);
//			VelocityEngine ve = new VelocityEngine();
//			ve.setProperty("runtime.log.logsystem.class","org.apache.velocity.runtime.log.NullLogChute");
//			ve.init(p);
//			Template t = ve.getTemplate("test_velocity.xml");
//			for (int i = 0; i < 3; i++) {
//				System.out.println("===================== "+(i+1)+" =====================");
//				// 加载模板文件
//				
//				StringWriter writer = new StringWriter();
//				t.merge(context, writer);
//				result = writer.toString();
//				writer.close();
//				System.out.print(result.toString());
//			}
			
//			for (int i = 0; i < 3; i++) {
//				VelocityContext context = new VelocityContext();
//				context.put("DLBH", "实得分是否是放松放松"+(i*100));
//				System.out.println(TemplateToolV1218.fill(context, tmdir, "test_velocity.xml", inputEncode, ouputEncode));
//			}
			
			String POINTCUT  = "<aop:pointcut id=\"$!{ADVICEREF}PointCut$!{INDEX}\"  expression=\"execution(* $!{PKG}.*(..))\" />"; 
			VelocityContext context = new VelocityContext();
			context.put("ADVICEREF", "dataSourceAdvice");
			context.put("INDEX", "1");
			context.put("PKG", "com.multi");
			context.put("ORDER", "2");
			result = TemplateToolV1218.fill(context, POINTCUT);
			System.out.println("result: "+result);
			
			String ADVISER   = "<aop:advisor advice-ref=\"$!{ADVICEREF}\"   pointcut-ref=\"$!{ADVICEREF}AdvicePointCut$!{INDEX}\" order=\"$!{ORDER}\" />"; 
			result = TemplateToolV1218.fill(context, ADVISER);
			System.out.println("result: "+result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
