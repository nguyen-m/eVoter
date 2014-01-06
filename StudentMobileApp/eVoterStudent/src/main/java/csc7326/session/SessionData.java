package csc7326.session;

/**
 * Created by luongnv89 on 06/12/13.
 */
public class SessionData {
    String id;
    String name;
    String teacherName;
    String status;
    String createDate;
    boolean isActive;

    public SessionData(String id, String name, String teacherName, String status, String createDate) {
        this.id = id;
        this.name = name;
        this.teacherName = teacherName;
        this.status = status;
        this.createDate = createDate;
        this.isActive = false;
    }

    public SessionData(SessionData copy) {
        this.id = copy.getId();
        this.name = copy.getName();
        this.teacherName = copy.getTeacherName();
        this.status = copy.getStatus();
        this.createDate = copy.getCreateDate();
        this.isActive = copy.isActive();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //Get methods
    public String getName() {
        return name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getStatus() {
        return status;
    }

    public String getCreateDate() {
        return createDate;
    }

    //Set methods


    public void setName(String name) {
        this.name = name;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}