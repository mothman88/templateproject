package net.mothman.feedgenerator.jms.sender;

import javax.jms.JMSException;
import javax.jms.Queue;

import net.mothman.feedgenerator.domain.PlayerDetails;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class LinearMessageSender {
	
	private static final Logger logger = Logger.getLogger(LinearMessageSender.class);

	@Autowired
	@Qualifier("jmsTemplate")
	private JmsTemplate jmsTemplate;
	@Autowired
	@Qualifier("queueLinear")
	private Queue queue;

	public void sendMessage(PlayerDetails message) throws JMSException {
		logger.debug("About to put message on queue. Queue[" + queue.toString() + "] Message[" + message + "]");
		jmsTemplate.convertAndSend(queue, message);
	}
	
}