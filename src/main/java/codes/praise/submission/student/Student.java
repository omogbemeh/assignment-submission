package codes.praise.submission.student;

import codes.praise.submission.model.Role;
import codes.praise.submission.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Student extends User {
    private Role role = Role.STUDENT;
}
