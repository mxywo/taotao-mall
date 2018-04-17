package pojo;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/1/4 10:58
 */
public class EUTreeNode {
    private long id;
    private String text;
    private String state;//如果为'closed'的时候，将不自动展开该节点(父节点)。


    public EUTreeNode() {
    }

    public EUTreeNode(long id, String text, String state) {
        this.id = id;
        this.text = text;
        this.state = state;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}
