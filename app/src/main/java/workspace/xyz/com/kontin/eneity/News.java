package workspace.xyz.com.kontin.eneity;

public class News {
    @Override
    public String toString() {
        return "News{" +
                "create_time='" + create_time + '\'' +
                ", id=" + id +
                ", image_url='" + image_url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    private String create_time;
    private int id;
    private String image_url;
    private String name;

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
