package kr.or.ddit.meeting.stomp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MeetingStatusMessageController {
	// /app/status desination으로 발생한 메시지를 수신함
	@MessageMapping("/status")
	@SendTo
	public DummyStompVO messageRelay(@Payload DummyStompVO dummy) {
		log.info("수신된 메시지 : {}}", dummy);
		return dummy;
	}
}
