package com.hello.project.domain.subscribe;

import com.hello.project.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "from_id")
    @ManyToOne
    private User fromUser;

    @JoinColumn(name = "to_id")
    @ManyToOne
    private User toUser;


}
