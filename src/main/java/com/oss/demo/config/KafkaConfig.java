package com.oss.demo.config;

import com.oss.demo.eventModel.PaymentEvent;
import com.oss.demo.eventModel.OrderEvent;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

	private static final String BOOTSTRAP_SERVERS = "localhost:29092"; // Update with your Kafka server

	// KafkaTemplate for PaymentEvent
	@Bean
	public KafkaTemplate<String, PaymentEvent> paymentKafkaTemplate() {
		return new KafkaTemplate<>(paymentProducerFactory());
	}

	private ProducerFactory<String, PaymentEvent> paymentProducerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfig(PaymentEventSerializer.class));
	}

	// KafkaTemplate for OrderEvent
	@Bean
	public KafkaTemplate<String, OrderEvent> orderKafkaTemplate() {
		return new KafkaTemplate<>(orderProducerFactory());
	}

	private ProducerFactory<String, OrderEvent> orderProducerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfig(OrderEventSerializer.class));
	}

	// Common producer config method
	private Map<String, Object> producerConfig(Class<?> valueSerializer) {
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
		return config;
	}

	private ConsumerFactory<String, OrderEvent> orderConsumerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "order_group");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

		JsonDeserializer<OrderEvent> jsonDeserializer = new JsonDeserializer<>(OrderEvent.class);
		jsonDeserializer.addTrustedPackages("*"); // Allow deserialization of all packages

		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), jsonDeserializer);
	}

	private ConsumerFactory<String, PaymentEvent> paymentConsumerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "payment_group");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

		JsonDeserializer<PaymentEvent> jsonDeserializer = new JsonDeserializer<>(PaymentEvent.class);
		jsonDeserializer.addTrustedPackages("*"); // Allow deserialization of all packages

		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), jsonDeserializer);
	}

	// Consumer Configuration for OrderEvent
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, OrderEvent> orderKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, OrderEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(orderConsumerFactory());
		return factory;
	}

	// Consumer Configuration for PaymentEvent
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, PaymentEvent> paymentKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, PaymentEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(paymentConsumerFactory());
		return factory;
	}

}
