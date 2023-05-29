package codes.praise.submission.codereviewer;

import codes.praise.submission.assignment.Assignment;
import codes.praise.submission.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Table(name = "code_reviewer")
public class CodeReviewer extends User {
    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<Assignment> assignments;
}