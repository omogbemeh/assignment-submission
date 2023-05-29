package codes.praise.submission.assignment;

import codes.praise.submission.codereviewer.CodeReviewer;
import codes.praise.submission.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Assignment implements Serializable {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student assignedTo;
    @ManyToOne()
    @JoinColumn(name = "code_reviewer_id")
    private CodeReviewer reviewedBy;
}
