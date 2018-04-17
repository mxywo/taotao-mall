package pojo;

import java.util.List;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/1/3 13:49
 */
public class EUDadaGridResult {
    private long total;
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
