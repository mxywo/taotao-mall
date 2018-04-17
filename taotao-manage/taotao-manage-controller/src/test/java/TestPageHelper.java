//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.taotao.mapper.TbItemMapper;
//import com.taotao.pojo.TbItem;
//import com.taotao.pojo.TbItemExample;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import java.util.List;
//
///**
// * @auther Mxy 80103005
// * @date : Creat in 2017/12/29 17:09
// */
//public class TestPageHelper {
//    @Test
//    public void testPageHelper() {
////        创建一个Spring容器，从容器中获得Mapper的代理对象，执行查询并分页
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("" +
//                "classpath:spring/applicationContext-*.xml");
//        TbItemMapper mapper = applicationContext.getBean(TbItemMapper.class);
//        TbItemExample example = new TbItemExample();
//        PageHelper.startPage(1,30);//页数，每页条数
//        //紧跟着的第一个select方法会被分页
//        List<TbItem> list = mapper.selectByExample(example);
//        for(TbItem tbItem : list){
//            System.out.println(tbItem.getTitle());
//        }
//        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
//        long total = pageInfo.getTotal();
//        System.out.println(total);
//    }
//}
