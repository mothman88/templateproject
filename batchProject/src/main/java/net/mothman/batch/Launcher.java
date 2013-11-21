package net.mothman.batch;

import java.util.Date;

import net.mothman.project.jobs.AbstractJob;
import net.mothman.project.jobs.LeftMenuBrickJob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * The application initializer:
 *  
 * @author mothman88
 */
public class Launcher {

	private static final Logger log = LoggerFactory.getLogger(Launcher.class);
	
	public static void main(String[] args) {
		log.info("START JOB : {}", new Date());
		
		ApplicationContext context = null;
		try {
			context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);
			
			AbstractJob [] jobs = {
				context.getBean(LeftMenuBrickJob.class),
			};
			
			
			// Execute jobs
			for(AbstractJob job : jobs) {
				job.execute();
			}
	        
			
		} catch (Exception e) {
			log.error("fatal error program killed", e);
		} finally {
			if (context != null)
                ((AbstractApplicationContext) context).close();
		}
		
		log.info("END JOB : {}", new Date());
	}
	
//	final Map<String, Object> myMaps = context.getBeansWithAnnotation(Engine.class);
//    System.out.println("inside after properties set" + myMaps );//**Always giving an empty set**
//    for (final Object myMap : myMaps.values()) {
//        final Class<? extends Object> myClass = myMap.getClass();
//        AbstractJob job = (AbstractJob) context.getBean(myClass);
//        job.execute();
////        final MyJob annotation = myClass.getAnnotation(MyJob.class);
////        System.out.println("Found myClass: " + myClass + ", with tags: " + annotation.name());
//        
//    }
	
}
