package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IdListDTO implements Serializable {
    private List<Integer> idList;
    public IdListDTO(List<Integer> idList){
        this.idList = new ArrayList<>();
        this.idList.addAll(idList);
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList.clear();
        this.idList.addAll(idList);
    }
}
