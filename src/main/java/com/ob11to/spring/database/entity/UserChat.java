package com.ob11to.spring.database.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users_chat")
public class UserChat implements BaseEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Chat chat;

    public void setUser(User user){
        this.user = user;
        this.user.getUserChats().add(this);
    }

    public void setChat(Chat chat){
        this.chat = chat;
        this.chat.getUserChats().add(this);
    }
}
