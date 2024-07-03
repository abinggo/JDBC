package durid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

public class druiddemo {
    public static void main(String[] args) throws Exception {
        //1 导入jar包

        //2 定义配置文件

        //3 加载配置文件

        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));

        //4 获取链接池对象

        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //获取数据库链接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        //后面就正常相同了
        //获取当前根目录
       // System.out.println(System.getProperty("use.dir"));

    }
}
