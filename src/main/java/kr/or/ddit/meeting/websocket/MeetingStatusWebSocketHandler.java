package kr.or.ddit.meeting.websocket;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MeetingStatusWebSocketHandler extends TextWebSocketHandler{
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	@Value("${contentFile}")
	private Path contentFile;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionList.add(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Files.writeString(contentFile, message.getPayload(), StandardOpenOption.WRITE);
		for(WebSocketSession single :  sessionList) {
			single.sendMessage(message);
		}
		
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionList.remove(session);
	}

}
