package kr.or.ddit.meeting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MeetingStatusController {
	
	@Value("${contentFile}")
	private Path contentFile;
	
	@GetMapping("/meeting")
	public String meetingView(Model model) throws IOException {
		if(Files.exists(contentFile)) { // 파일이 있으면
			String tableContent = Files.readString(contentFile);
			model.addAttribute("tableContent", tableContent);			
		}
		return "meeting/status";
	}
	
	@GetMapping("/meeting/stomp")
	public String meetingViewForStomp(Model model) throws IOException {
		if(Files.exists(contentFile)) { // 파일이 있으면
			String tableContent = Files.readString(contentFile);
			model.addAttribute("tableContent", tableContent);			
		}
		return "meeting/status-stomp";
	}
}
