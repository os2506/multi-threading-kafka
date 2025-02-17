package com.oss.demo.config;

import com.oss.demo.eventModel.OrderEvent;
import com.oss.demo.eventModel.PaymentEvent;
//import com.oss.demo.eventModel.EnrichedOrderEvent;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
//import org.apache.kafka.streams.kstream.StreamJoined;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

//import java.time.Duration;
//import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaStreamsConfig {

	// private static final Logger logger =
	// LoggerFactory.getLogger(KafkaStreamsConfig.class);

	// Create a Jackson serializer for OrderEvent
	public static class OrderEventSerde extends Serdes.WrapperSerde<OrderEvent> {
		public OrderEventSerde() {
			super(new JsonSerializer<>(), new JsonDeserializer<>(OrderEvent.class));
		}
	}

	// Create a Jackson serializer for PaymentEventSerde
	public static class PaymentEventSerde extends Serdes.WrapperSerde<PaymentEvent> {
		public PaymentEventSerde() {
			super(new JsonSerializer<>(), new JsonDeserializer<>(PaymentEvent.class));
		}
	}

	@Bean
	public KafkaStreams kafkaStreams() {
		// Kafka Streams configuration
		Map<String, Object> props = new HashMap<>();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "events-app");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());

		// Stream construction
		StreamsBuilder builder = new StreamsBuilder();

		// Kafka Streams for Order Event and Payment Event
		KStream<String, OrderEvent> orderStream = builder.stream("order-events");
	//	KStream<String, PaymentEvent> paymentStream = builder.stream("payment-events");

		orderStream.to("combined_ev");

		// Create and start KafkaStreams
		KafkaStreams streams = new KafkaStreams(builder.build(), new StreamsConfig(props));
		streams.start();

		return streams;
	}

}
