package net.mothman.project.service;

import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mothman.project.exceptions.GenericSystemException;
import net.mothman.project.exceptions.ServiceException;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * @author mothman88
 *
 */
public class FreemarkerService {

	/**
	 * 
	 * @param file
	 * @param templateFile
	 * @param input
	 * @throws ServiceException
	 */
	public void process(String file, String templateFile, Map<String, Object> input) throws ServiceException {
		// Configuration
		FileWriter writer = null;
		try {
			Configuration cfg = new Configuration();
			cfg.setClassForTemplateLoading(this.getClass(), "/");
			
			// load template
			Template template = cfg.getTemplate(templateFile);
			template.setEncoding("UTF-8");
			
			// output
			writer = new FileWriter(file);
			template.process(input, writer);
			writer.flush();

		} catch (Throwable e) {
			throw new GenericSystemException(e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e2) {
				}
			}
		}

	}
	
	/**
	 * 
	 * @param templateFile
	 * @param input
	 * @return
	 * @throws ServiceException
	 */
	public String process(String templateFile, Map<String, Object> input)
			throws ServiceException {
		// Configuration
		Writer writer = null;
		try {
			Configuration cfg = new Configuration();
			cfg.setClassForTemplateLoading(this.getClass(), "/");

			// load template
			Template template = cfg.getTemplate(templateFile);
			template.setEncoding("UTF-8");
			
			// output
			writer = new StringWriter();
			template.process(input, writer);
			writer.flush();

			return writer.toString();
		} catch (Throwable e) {
			throw new GenericSystemException(e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e2) {
				}
			}
		}

	}
	
	public void process(String file, String templateFile, List<Map<String, Object>> input) throws ServiceException {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("data", input);

		process(file, templateFile, m);
	}
}
