package beans;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class ResultListBean implements Serializable {
    private List<ResultBean> results;

    public ResultListBean(List<ResultBean> results){
        this.results = results;
    }

    public ResultListBean(){
        this.results = new ArrayList<>();
    }

    public List<ResultBean> getResults(){
        return results;
    }

    public void setResults(List<ResultBean> results){
        this.results = results;
    }
}