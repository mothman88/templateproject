package net.mothman.feedgenerator.jms.listener;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import net.mothman.feedgenerator.domain.PlayerDetails;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

@Component
public class LinearMessageListener implements MessageListener {

	private static final Logger logger = Logger.getLogger(LinearMessageListener.class);
	
	@Value("${template.linear}") 
	private String xslTemplate;
	
	@Value("${gsa.url}") 
	private String gsaUrl;
	
	@Value("${gsa.datasource}") 
	private String gsaDatasource;
	
	@Value("${gsa.feedtype}") 
	private String gsaFeedType;
	
	@Autowired 
	private MessageConverter messageConverter;
	
	public void onMessage(Message message) {
		logger.debug("Received message from queue [" + message + "]");

		try {
			PlayerDetails playerDetails = (PlayerDetails) messageConverter.fromMessage(message);
			logger.info(playerDetails);
			
			
		} catch (JMSException e1) {
			e1.printStackTrace();
		}
		
//		/* The message must be of type TextMessage */
//		if (message instanceof TextMessage) {
//			try {
//				String msgText = ((TextMessage) message).getText();
//				logger.info("[LINEAR]About to process message: " + msgText);
//
//				// TODO: leggo file xml
//				TransformerFactory factory = TransformerFactory.newInstance();
//				Source xslt = new StreamSource(LinearMessageListener.class.getResourceAsStream(this.xslTemplate));
//				Transformer transformer = factory.newTransformer(xslt);
//
//				Source text = new StreamSource(new File(msgText));
//				transformer.transform(text, new StreamResult(new File("data/linear_output" + System.currentTimeMillis() + ".xml")));
//					
//				//EncoderFeederJob.class.getResource("/vewms-encoderfeeder-log4j.xml")
//					
//			} catch (JMSException jmsEx_p) {
//				String errMsg = "An error occurred extracting message";
//				logger.error(errMsg, jmsEx_p);
//			} catch (TransformerConfigurationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (TransformerException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} else {
//			String errMsg = "Message is not of expected type TextMessage";
//			logger.error(errMsg);
//			throw new RuntimeException(errMsg);
//		}
	}
	
	
	/**
	 * 
	 * @param xmlFeed
	 */
	public void sendFeed(File xmlFeed, String feedType, String dataSource) {
		HttpClient client = new DefaultHttpClient();
		try {
			
			HttpPost        post   = new HttpPost( "http://10.73.17.49:19900/xmlfeed" );
			MultipartEntity entity = new MultipartEntity( HttpMultipartMode.BROWSER_COMPATIBLE );
			
			entity.addPart("data", new FileBody(xmlFeed, "text/xml" ));
			entity.addPart("feedtype", new StringBody(feedType, "text/plain", Charset.forName( "UTF-8" )));
			entity.addPart("datasource", new StringBody(dataSource, "text/plain", Charset.forName( "UTF-8" )));
			
			post.setEntity(entity);
			
			HttpResponse response = client.execute(post);
			int respCode = response.getStatusLine().getStatusCode();
			if(respCode != HttpStatus.OK.value()) {
				System.out.println("ERRORE FEED NON INVIATO");
			}
			
		} catch (IOException e) {
			// TODO create a custom exception
			throw new RuntimeException(e);
		}finally {
			client.getConnectionManager().shutdown();
			client = null;
		}
	}
}
