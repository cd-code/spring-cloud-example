package me.cauley.servicezuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class ServiceZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceZuulApplication.class, args);
	}

	@Bean
	public FallbackProvider fallbackProvider(){
		return new FallbackProvider() {
			@Override
			public String getRoute() {
				//service name, namely serviceId in zuul.routes
				return "service-ribbon";
			}

			@Override
			public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
				return new ClientHttpResponse() {
					@Override
					public HttpStatus getStatusCode() throws IOException {
						return HttpStatus.SERVICE_UNAVAILABLE;
					}

					@Override
					public int getRawStatusCode() throws IOException {
						return 503;
					}

					@Override
					public String getStatusText() throws IOException {
						return "Service Unavailable";
					}

					@Override
					public void close() {

					}

					@Override
					public InputStream getBody() throws IOException {
						return new ByteArrayInputStream("Ribbon Service is down".getBytes());
					}

					@Override
					public HttpHeaders getHeaders() {
						HttpHeaders headers = new HttpHeaders();
						headers.setContentType(MediaType.APPLICATION_JSON);
						return headers;
					}
				};
			}
		};
	}
}
