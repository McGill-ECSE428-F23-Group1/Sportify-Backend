package ca.mcgill.ecse428.group1.sportifybackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.group1.sportifybackend.dao.ChatRepository;
import ca.mcgill.ecse428.group1.sportifybackend.model.Chat;
import ca.mcgill.ecse428.group1.sportifybackend.model.Member;

@Service
public class ChatService {
    @Autowired
    ChatRepository chatRepository;

    @Autowired
    MemberService memberService;

    @Transactional
    // initially no message in the chat
    public Chat createChat(String member1Username, String member2Username) throws IllegalArgumentException {
        if (member1Username == null || member1Username.trim().length() == 0) {
            throw new IllegalArgumentException("Member1 username cannot be empty!");
        }
        if (member2Username == null || member2Username.trim().length() == 0) {
            throw new IllegalArgumentException("Member2 username cannot be empty!");
        }
        // check if chat already exists:
        if (checkIfChatExists(member1Username, member2Username)) {
            throw new IllegalArgumentException("Chat already exists!");
        }
        Member member1 = memberService.getMember(member1Username);
        Member member2 = memberService.getMember(member2Username);
        Chat chat = new Chat();
        chat.setMember1(member1);
        chat.setMember2(member2);
        chatRepository.save(chat);
        return chat;
    }

    @Transactional
    public List<Chat> getChatsByMember(String memberUsername) throws IllegalArgumentException {
        if (memberUsername == null || memberUsername.trim().length() == 0) {
            throw new IllegalArgumentException("Member username cannot be empty!");
        }
        Member member = memberService.getMember(memberUsername);
        List<Chat> chats = chatRepository.findChatsByMember1(member);
        chats.addAll(chatRepository.findChatsByMember2(member));
        return chats;
    }

    @Transactional
    public Chat getChatByMembers(String member1Username, String member2Username) throws IllegalArgumentException {
        if (member1Username == null || member1Username.trim().length() == 0) {
            throw new IllegalArgumentException("Member1 username cannot be empty!");
        }

        if (member2Username == null || member2Username.trim().length() == 0) {
            throw new IllegalArgumentException("Member2 username cannot be empty!");
        }

        Member member1 = memberService.getMember(member1Username);
        Member member2 = memberService.getMember(member2Username);

        Chat chat = chatRepository.findChatByMember1AndMember2(member1, member2);
        Chat chat2 = null;
        if (chat == null) {
            chat2 = chatRepository.findChatByMember1AndMember2(member2, member1);
        }

        if (chat == null && chat2 == null) {
            throw new IllegalArgumentException("Chat does not exist!");
        }

        return chat == null ? chat2 : chat;
    }

    @Transactional
    public boolean checkIfChatExists(String member1Username, String member2Username) throws IllegalArgumentException {
        if (member1Username == null || member1Username.trim().length() == 0) {
            throw new IllegalArgumentException("Member1 username cannot be empty!");
        }
        if (member2Username == null || member2Username.trim().length() == 0) {
            throw new IllegalArgumentException("Member2 username cannot be empty!");
        }

        Member member1 = memberService.getMember(member1Username);
        Member member2 = memberService.getMember(member2Username);

        if (member1 == null || member2 == null) {
            throw new IllegalArgumentException("Member does not exist!");
        }

        Chat chat = chatRepository.findChatByMember1AndMember2(member1, member2);
        Chat chat2 = null;
        if (chat == null) {
            chat2 = chatRepository.findChatByMember1AndMember2(member2, member1);
        }

        if (chat == null && chat2 == null) {
            return false;
        }
        return true;
    }

    @Transactional
    public void deleteChat(String member1Username, String member2Username) throws IllegalArgumentException {
        if (member1Username == null || member1Username.trim().length() == 0) {
            throw new IllegalArgumentException("Member1 username cannot be empty!");
        }
        if (member2Username == null || member2Username.trim().length() == 0) {
            throw new IllegalArgumentException("Member2 username cannot be empty!");
        }
        Member member1 = memberService.getMember(member1Username);
        Member member2 = memberService.getMember(member2Username);
        if (member1 == null || member2 == null) {
            throw new IllegalArgumentException("Member does not exist!");
        }
        Chat c = chatRepository.findChatByMember1AndMember2(member1, member2);
        Chat c2 = null;
        if (c == null) {
            c2 = chatRepository.findChatByMember1AndMember2(member2, member1);
        }
        if (c == null && c2 == null) {
            throw new IllegalArgumentException("Chat does not exist!");
        }
        chatRepository.delete(c == null ? c2 : c);
    }
}