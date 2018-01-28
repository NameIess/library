package model;

import java.io.Serializable;

public class Role implements Identified, Serializable {
    public static final String ID_ALIAS = "id";
    public static final String NAME_ALIAS = "name";
    public static final String FOREIGN_KEY_ID_ALIAS = "role_id";
    public static final String TABLE_NAME = "role";
    public static final Long ANONYMOUS_ROLE_ID = 0L;
    public static final Long READER_ROLE_ID = 3L;
    public static final Long LIBRARIAN_ROLE_ID = 2L;
    public static final Long ADMIN_ROLE_ID = 1L;
    private Long id;
    private String name;


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Role)) {
            return false;
        }

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) {
            return false;
        }

        return name != null ? name.equals(role.name) : role.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
