package data;

import java.io.Serializable;

public class IdDTO implements Serializable {
    private int id;
    public IdDTO(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
