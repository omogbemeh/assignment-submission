package codes.praise.submission.model;

import lombok.Getter;

public enum Permission {
    CODE_REVIEWER_CREATE("code_reviewer:create"),
    CODE_REVIEWER_READ("code_reviewer:read"),
    CODE_REVIEWER_UPDATE("code_reviewer:update"),
    CODE_REVIEWER_DELETE("code_reviewer:delete"),
    ADMIN_CREATE("admin:read"),
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
