package domain;

public abstract class BaseEntity {
    static long id = 0;


    public BaseEntity() {
        increaseId();
    }

    public synchronized void increaseId(){
        this.id++;
    }

    public static long getId() {
        return id;
    }

    public static void setId(long id) {
        BaseEntity.id = id;
    }
}
