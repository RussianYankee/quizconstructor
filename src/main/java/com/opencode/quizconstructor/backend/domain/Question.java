package com.opencode.quizconstructor.backend.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "questions")
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
    @Column(name = "question_text")
    private String text;
    @Column(name = "question_type")
    @Enumerated(EnumType.ORDINAL)
    private QuestionChoiseType questionType;

    @OneToMany(mappedBy = "question",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    List<Answer> answers;

    public Question(Quiz quiz, String text,
                    QuestionChoiseType questionType, List<Answer> answers) {
        this.quiz = quiz;
        this.text = text;
        this.questionType = questionType;
        this.answers = answers;
    }
}
