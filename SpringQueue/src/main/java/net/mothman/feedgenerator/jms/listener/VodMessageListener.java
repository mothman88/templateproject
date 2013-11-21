package net.mothman.feedgenerator.jms.listener;

import java.io.File;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VodMessageListener implements MessageListener {

	private static final Logger logger = Logger.getLogger(VodMessageListener.class);
	
	@Value("${template.vod}")
	private String xslTemplate;

	public void onMessage(Message message) {
		logger.debug("Received message from queue [" + message + "]");

		/* The message must be of type TextMessage */
		if (message instanceof TextMessage) {
			try {
				String msgText = ((TextMessage) message).getText();
				logger.info("[VOD]About to process message: " + msgText);

				TransformerFactory factory = TransformerFactory.newInstance();
				Source xslt = new StreamSource(VodMessageListener.class.getResourceAsStream(this.xslTemplate));
				Transformer transformer = factory.newTransformer(xslt);

				Source text = new StreamSource(new File(msgText));
				transformer.transform(text, new StreamResult(new File("data/vod_output" + System.currentTimeMillis() + ".xml")));
					
				//EncoderFeederJob.class.getResource("/vewms-encoderfeeder-log4j.xml")
					
			} catch (JMSException jmsEx_p) {
				String errMsg = "An error occurred extracting message";
				logger.error(errMsg, jmsEx_p);
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			String errMsg = "Message is not of expected type TextMessage";
			logger.error(errMsg);
			throw new RuntimeException(errMsg);
		}
	}
}
