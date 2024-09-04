package uz.ppdiary.pp_diary.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.ppdiary.pp_diary.entity.enums.ReactionType;

@Builder
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ReactionType reactionType;
    @ManyToOne
    private User reactedUser;
}