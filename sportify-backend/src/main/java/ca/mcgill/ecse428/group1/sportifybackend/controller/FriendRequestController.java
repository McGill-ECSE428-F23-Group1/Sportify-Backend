package ca.mcgill.ecse428.group1.sportifybackend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.socket.WebSocketSession;
// import org.springframework.web.util.HtmlUtils;
// import org.springframework.messaging.handler.annotation.MessageMapping;
// import org.springframework.messaging.handler.annotation.SendTo;

import ca.mcgill.ecse428.group1.sportifybackend.dto.FriendRequestDto;
import ca.mcgill.ecse428.group1.sportifybackend.service.FriendRequestService;
import ca.mcgill.ecse428.group1.sportifybackend.model.FriendRequest;

@CrossOrigin(origins = "*")
@RestController
public class FriendRequestController {

    @Autowired
    private FriendRequestService friendRequestService;

    @PostMapping(value = { "/friendRequest", "/friendRequest/" })
    public FriendRequestDto createFriendRequest(@RequestParam String message,
            @RequestParam String senderUsername, @RequestParam String receiverUsername) throws IllegalArgumentException {
        return convertToDto(friendRequestService.createFriendRequest(message, senderUsername,receiverUsername));
    }

    @PutMapping(value = { "/friendRequest/updateStatus/{id}", "/friendRequest/updateStatus/{id}/" })
    public FriendRequestDto updateFriendRequestStatus(@PathVariable("id") int id, @RequestParam String status) {
        return convertToDto(friendRequestService.updateFriendRequestStatus(id, status));
    }

    @GetMapping(value = { "/friendRequest/{id}", "/friendRequest/{id}/" })
    public FriendRequestDto getFriendRequestById(@PathVariable("id") int id) {
        return convertToDto(friendRequestService.getFriendRequestById(id));
    }

    @GetMapping(value = { "/friendRequest/sender/{username}", "/friendRequest/sender/{username}/" })
    public List<FriendRequestDto> getFriendRequestBySender(@PathVariable("username") String username) {
        return friendRequestService.getFriendRequestBySender(username).stream().map(fr -> convertToDto(fr)).collect(Collectors.toList());
    }

    @GetMapping(value = { "/friendRequest/receiver/{username}", "/friendRequest/receiver/{username}/" })
    public List<FriendRequestDto> getFriendRequestByReceiver(@PathVariable("username") String username) {
        return friendRequestService.getFriendRequestByReceiver(username).stream().map(fr -> convertToDto(fr)).collect(Collectors.toList());
    }

    @DeleteMapping(value = { "/friendRequest/{id}", "/friendRequest/{id}/" })
    public FriendRequestDto deleteFriendRequest(@PathVariable("id") int id) {
        return convertToDto(friendRequestService.deleteFriendRequest(id));
    }

    private FriendRequestDto convertToDto(FriendRequest friendRequest) {
        if (friendRequest == null) {
            throw new IllegalArgumentException("Friend request does not exist!");
        }
        MemberController memberController = new MemberController();
        FriendRequestDto friendRequestDto = new FriendRequestDto(friendRequest.getId(), friendRequest.getMessage(),
                friendRequest.getStatus(), friendRequest.getTime(), memberController.convertToDto(friendRequest.getSender()),
                memberController.convertToDto(friendRequest.getReceiver()));
        return friendRequestDto;
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
