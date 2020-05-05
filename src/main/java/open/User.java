package open;

public class User {
    private String name;
    private String job;

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public User(String name, String job) {
        this.name = name;
        this.job = job;
    }
}
