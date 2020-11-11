//package com.prokarma.producer.config;
//
//import java.util.HashMap;
//import java.util.Map;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//@Configuration
//public class KafkaPublisherConfig {
//
//	@Value("${spring.kafka.bootstrap-servers}")
//	private String bootStrapServer;
//
//	@Bean
//	public ProducerFactory<String, Object> producerFactory() {
//		Map<String, Object> configs = new HashMap<>();
//		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
//		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//		configs.put("spring.kafka.properties.sasl.jaas.config",
//				"org.apache.kafka.common.security.scram.ScramLoginModule required username=\"j2fft6jv\" password=\"qDxwWd1vo6FH02vvoiEZ2kaK7iRSBeg-\"");
//		configs.put("spring.kafka.properties.security.protocol", "SASL_SSL");
//		configs.put("spring.kafka.properties.sasl.mechanism", "SCRAM-SHA-256");
//		return new DefaultKafkaProducerFactory<>(configs);
//
//	}
//
//	@Bean
//	public KafkaTemplate<String, Object> kafkaTemplate() {
//		return new KafkaTemplate<>(producerFactory());
//	}
//
//}
