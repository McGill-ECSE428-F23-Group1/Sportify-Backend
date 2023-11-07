package ca.mcgill.ecse428.group1.sportifybackend.dto;

import java.util.List;

public class ChatDto {
    private int id;
    private List<MemberDto> memberDtos;
    private List<MessageDto> messageDtos;

    public ChatDto(int id, List<MemberDto> memberDtos, List<MessageDto> messageDtos) {
        this.id = id;
        this.memberDtos = memberDtos;
        this.messageDtos = messageDtos;
    }

    public int getId() {
        return this.id;
    }

    public List<MemberDto> getMemberDtos() {
        return this.memberDtos;
    }

    public List<MessageDto> getMessageDtos() {
        return this.messageDtos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMemberDtos(List<MemberDto> memberDtos) {
        this.memberDtos = memberDtos;
    }

    public void setMessageDto(List<MessageDto> messageDtos) {
        this.messageDtos = messageDtos;
    }

}
