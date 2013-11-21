package net.mothman.project.jobs;

import net.mothman.project.service.EmailAlertService;
import net.mothman.project.service.Engine;
import net.mothman.project.service.FreemarkerService;
import net.mothman.project.service.SQLiteDatabaseService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * 
 * @author mothman88
 *
 */
@Engine(name="leftmenu", priority=1)
public class LeftMenuBrickJob extends AbstractJob {
	
	private static final Logger log = LoggerFactory.getLogger(LeftMenuBrickJob.class);
	
	@Autowired private Environment env;
	@Autowired private FreemarkerService freemarkerService;
	@Autowired private SQLiteDatabaseService sqLiteDatabaseService;
	@Autowired private EmailAlertService emailAlertService;
	
	@Override
	public void execute() {
		log.info("START");
		
		// JOB Execution
		
		log.info("END");
	}
}
