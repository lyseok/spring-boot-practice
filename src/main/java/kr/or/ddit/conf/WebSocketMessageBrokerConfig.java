package kr.or.ddit.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketMessageBrokerConfig implements WebSocketMessageBrokerConfigurer{
   
   // 어떤 주소로 요청이 들어왔을 떄 채널을 설정할것인지 써줌
   // 채널 개방
   @Override
   public void registerStompEndpoints(StompEndpointRegistry registry) {
      registry.addEndpoint("/ws/stomp");
   }
   
   // 중개자에 대한 구체적인 설정 여기서 해줌
   @Override
   public void configureMessageBroker(MessageBrokerRegistry registry) {
      // topic으로 시작하는 메세지는 내가 릴레이(중개)할 수 있어
      registry.enableSimpleBroker("/topic", "/queue");
      registry.setApplicationDestinationPrefixes("/app");
   }

}
