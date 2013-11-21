package net.mothman.feedgenerator.jms.sender;

import javax.jms.JMSException;
import javax.jms.Queue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class VodMessageSender {
	
	private static final Logger logger = Logger.getLogger(VodMessageSender.class);

	@Autowired
	@Qualifier("jmsTemplateText")
	private JmsTemplate jmsTemplate;
	@Autowired
	@Qualifier("queueVod")
	private Queue queue;

	public void sendMessage(String message) throws JMSException {
		logger.debug("About to put message on queue. Queue[" + queue.toString() + "] Message[" + message + "]");
		jmsTemplate.convertAndSend(queue, message);

	}

}