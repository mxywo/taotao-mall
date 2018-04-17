import org.aspectj.weaver.ast.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/3/6 14:19
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<String, String>();

        map.put("1", "fds");
        map.put("2", "valu");
//        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
//        list.add(map);
//        for (Map<String, String> m : list){
            for (String k : map.keySet()){
                System.out.println(k + " : " + map.get(k));
            }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }
        }
    }
//}
