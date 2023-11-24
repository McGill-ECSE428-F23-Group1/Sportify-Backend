package ca.mcgill.ecse428.group1.sportifybackend.dto;
import java.util.List;

public class ChatDto {
    
    private int id;
    private MemberDto member1;
    private MemberDto member2;
    private List<MessageDto> messages;

    public ChatDto() {

    }

    public ChatDto(int id, MemberDto member1, MemberDto member2, List<MessageDto> messages) {
        this.id = id;
        this.member1 = member1;
        this.member2 = member2;
        this.messages = messages;
    }

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public MemberDto getMember1() {
        return member1;
    }

    public void setMember1(MemberDto newMember1) {
        this.member1 = newMember1;
    }

    public MemberDto getMember2() {
        return member2;
    }

    public void setMember2(MemberDto newMember2) {
        this.member2 = newMember2;
    }

    public List<MessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDto> newMessages) {
        this.messages = newMessages;
    }

}
