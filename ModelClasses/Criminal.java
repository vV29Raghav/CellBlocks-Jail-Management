package ModelClasses;

public class Criminal {
    private String id;
    private String name;
    private int age;
    private String crimeType;
    private String status;

    public Criminal(String id, String name, int age, String crimeType) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.crimeType = crimeType;
        this.status = "At Large";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCrimeType() {
        return crimeType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Criminal{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", crimeType='" + crimeType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
