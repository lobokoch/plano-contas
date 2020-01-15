/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:56:10.144
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.planocontas;

import static br.com.kerubin.api.messaging.utils.Utils.isEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

import br.com.kerubin.api.database.core.ServiceContext;
import br.com.kerubin.api.messaging.core.DomainEventPublisher;

import static br.com.kerubin.api.messaging.constants.MessagingConstants.HEADER_TENANT;
import static br.com.kerubin.api.messaging.constants.MessagingConstants.HEADER_USER;

public class MessageAfterReceivePostProcessors implements MessagePostProcessor {
	
	private static final Logger log = LoggerFactory.getLogger(DomainEventPublisher.class);
		
	@Override
	public Message postProcessMessage(Message message) throws AmqpException {
		
		log.info(FinanceiroPlanoContasConstants.DOMAIN + "." + FinanceiroPlanoContasConstants.SERVICE + " receiving message: " + message);
		
		Object tenant = message.getMessageProperties().getHeaders().get(HEADER_TENANT);
		Object user = message.getMessageProperties().getHeaders().get(HEADER_USER);
		
		if (isEmpty(tenant) || isEmpty(user)) {
			log.error("Empty or null tenant/user received from broker in message header tenant: {}, user: {}, message: ", tenant, user, message);
			
			throw new IllegalStateException("Empty or null tenant/user received from broker in message header tenant: " + tenant + ", user: " + user);
		}
		
		ServiceContext.setTenant(tenant.toString());
		ServiceContext.setUser(user.toString());
		
		ServiceContext.setDomain(FinanceiroPlanoContasConstants.DOMAIN);
		ServiceContext.setService(FinanceiroPlanoContasConstants.SERVICE);
		
		return message;
	}

}

