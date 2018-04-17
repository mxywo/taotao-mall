//import org.apache.commons.net.ftp.FTP;
//import org.apache.commons.net.ftp.FTPClient;
//import org.junit.Test;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
///**
// * @auther Mxy 80103005
// * @date : Creat in 2018/1/11 11:15
// */
//
//public class FTPTest {
//    @Test
//    public void testFtpClient() throws IOException {
//        //创建一个ftp客户端对象
//        FTPClient ftpClient = new FTPClient();
//        //创建ftp连接
//        ftpClient.connect("127.2.2.5",8888);
//        //登录ftp服务器
//        ftpClient.login("80103005","665683");
//        //上传文件
//        //读取本地文件
//        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg"));
//        //设置上传文件的格式
//        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//        //设置上传的路径
//        ftpClient.changeWorkingDirectory("/www/image");
//        //参数1：服务端文档名；参数2：上传文档的inputStream
//        ftpClient.storeFile("test1.jpg",fileInputStream);
//        //关闭连接
//        fileInputStream.close();
//        ftpClient.logout();
//    }
//}
