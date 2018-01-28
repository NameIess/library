package model;

import java.io.Serializable;

public class Status implements Identified, Serializable {
    public static final long STATUS_TRANSFERRED_ID = 2L;
    public static final long STATUS_RETURNED_ID = 3L;
    public static final long STATUS_REJECTED_ID = 4L;
    public static final String ID_ALIAS = "id";
    public static final String NAME_ALIAS = "name";
    public static final String FOREIGN_KEY_ALIAS = "status_id";
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
        final StringBuilder sb = new StringBuilder("Status{");
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

        if (!(o instanceof Status)) {
            return false;
        }

        Status status = (Status) o;

        if (id != null ? !id.equals(status.id) : status.id != null) {
            return false;
        }

        return name != null ? name.equals(status.name) : status.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
