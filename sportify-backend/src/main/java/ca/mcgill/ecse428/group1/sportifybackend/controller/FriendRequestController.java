package ca.mcgill.ecse428.group1.sportifybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.util.HtmlUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import ca.mcgill.ecse428.group1.sportifybackend.dto.FriendRequestDto;
import ca.mcgill.ecse428.group1.sportifybackend.service.FriendRequestService;

@CrossOrigin(origins = "*")
@RestController
public class FriendRequestController {

    @Autowired
    private FriendRequestService friendRequestService;

    @PostMapping(value = { "/friendRequest/{id}", "/friendRequest/{id}/" })
    public FriendRequestDto createFriendRequest(@PathVariable("id") int id) {
        friendRequestService.createFriendRequest(id);
        return new FriendRequestDto(id);
    }

    

    // @MessageMapping("/chat")
    // @SendTo("/topic/chat")
    // public Greeting greeting(HelloMessage message) throws Exception {
    //   Thread.sleep(1000); // simulated delay
    //   return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    // }
    // @MessageMapping("/chat")
    // @SendTo("/topic/chat")
    // public String handleMessage(HelloMessage message, WebSocketSession session) {
    //     // Get the client's IP address from the WebSocketSession
    //     String clientIpAddress = session.getRemoteAddress().getAddress().getHostAddress();
    //     System.out.println("sessipn: " + session);
    //     System.out.println("Received message from client with IP address: " + clientIpAddress);
    //     // Your message handling logic here
        
    //     return "Received message from client with IP address: " + clientIpAddress;
    // }

    // @RequestMapping("/websocket")
    // public String getWebSocket() {
    //     return "ws-broadcast";
    // }
    
}
