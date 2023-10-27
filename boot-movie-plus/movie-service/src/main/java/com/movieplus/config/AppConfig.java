package com.movieplus.config;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import co.elastic.clients.util.ContentType;

@Configuration
public class AppConfig {

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
		jsonMessageConverter.setObjectMapper(new ObjectMapper());
		messageConverters.add(jsonMessageConverter);

//		restTemplate.setMessageConverters(messageConverters);

		return restTemplate;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);

		Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
		ObjectMapper objectMapper = objectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
		objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL,
				As.WRAPPER_ARRAY);

		serializer.setObjectMapper(objectMapper);

		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		template.setKeySerializer(stringRedisSerializer);
		template.setValueSerializer(serializer);
		template.setHashKeySerializer(stringRedisSerializer);
		template.setHashValueSerializer(serializer);
		template.afterPropertiesSet();
		return template;
	}

	@Bean
	public ObjectMapper objectMapper() {
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addDeserializer(LocalDateTime.class, new MillisOrLocalDateTimeDeserializer());
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(javaTimeModule);
		return objectMapper;
	}

	@SuppressWarnings("serial")
	public class MillisOrLocalDateTimeDeserializer extends LocalDateTimeDeserializer {
		public MillisOrLocalDateTimeDeserializer() {
			super(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		}

		@Override
		public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
			if (parser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
				long value = parser.getValueAsLong();
				Instant instant = Instant.ofEpochMilli(value);

				return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
			}

			return super.deserialize(parser, context);
		}

	}

	@Bean
	public ElasticsearchClient elasticsearchClient() {
		// Create the low-level client
		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("", ""));

		RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200))
				.setHttpClientConfigCallback(httpClientConfigCallback -> {
					httpClientConfigCallback.disableAuthCaching();
					httpClientConfigCallback.setDefaultHeaders(
							List.of(new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON)));
					httpClientConfigCallback.addInterceptorLast((HttpResponseInterceptor) (response,
							context) -> response.addHeader("X-Elastic-Product", "Elasticsearch"));
					return httpClientConfigCallback.setDefaultCredentialsProvider(credentialsProvider);
				}).build();

		// Create the transport with a Jackson mapper
		ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

		return new ElasticsearchClient(transport);
	}
}
