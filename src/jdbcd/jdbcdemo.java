package jdbcd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
* JDBC快速入门*/
public class jdbcdemo {
    public static void main(String[] args) throws Exception {
        // 1 注册驱动
        Class.forName("com.mysql.jdbc.Driver");//Mysql5之后的驱动包，可以省略不写
        // 在jar包中/java.sql.Driver文件中的驱动类
        // 2 获取链接
        String url="jdbc:mysql://127.0.0.1:3306/test?useSSL=false";
        //默认格式：jdbc:mysql://地址:端口/数据库名?useSSL=false&useServerPrestmts=true; 使用预编译功能需要自己点开
        String username="root";
        String password="1234";
        //DriverManager()
        Connection con = DriverManager.getConnection(url, username, password);
        //Connection获取执行sql的对象，管理实务
        /*
         * 管理事务
         * 开启事务：con.setAutoCommit(boolean autoCommit):true为自动提交,false为手动提交即开启
         * 提交：con.commit()
         * 回滚:con.rollback() 使用try catch里面的catch进行提交事务
         * */
        // 3 定义sql语句
        String sql = "select * from score";
        // 4 获取sql执行对象
        // Statement(普通),PreparedStatement(预编译SQL的执行SQL对象，防止SQL注入），CallableStatement prepareCall(sql)存储执行过程
        /* int executeUpdate(sql) 执行DML,DDL语句,返回值受影响的行数
           ResultSet executeQuery(sql) 执行DQL语句，返回结果集合对象，游标初始指向表头
           //方法 1 boolean next() 将光标向下移动一行，判断是否为有效行，true则说明有数据
           //方法 2  getXXX(参数)：获取数据 参数： 1 传入int 列的编号，从1开始，String 传入的是列的名称，一般用循环
           // while(rs.next()){rs.get}
           /*
           String sql = "select * from score";
           Statement stmt = con.createStatement();
           ResultSet rs = stmt.executeQuery(sql);
           while(rs.next()){
           int id = rs.getInt(1);//int id = rs.getInt("id);
           String name = rs.getString(2);
           int mathscore = rs.getInt(3);}
         */
        //preparedStatement
        //SQL注入：通过操作输入来修改事先定义好的SQL语句，从而达到执行代码对服务器进行攻击的方法
        /*sql注入演示
        *  // 接收用户输入 用户名和密码
        String name = "zhangsan";
        String pwd = "' or '1' = '1";

        // 定义sql
        String sql = "select * from tb_user where username = ? and password = ?";
        * 可以发现输出的SQL语句是 select * from tb_user where username = “zhangsan” and password = ''or'1'='1';
        * 这个语句被拆成语句了。。。666
        * String sql = "select * from tb_user where username = ? and password = ?";
        * preparedStatement pstmt = con.preparedStatement(sql);
        * pstmt.setString(1,name); 第一个代表第一个问号
        * pstmt.setString(2,password);
        * //执行,其实就是加了转义字符
        * pstmt.executeQuery();
        *jdbc:mysql://地址:端口/数据库名?useSSL=false&useServerPrestmts=true; 使用预编译功能需要自己点开
        * 性能更高，转义敏感字符
        * */



        Statement stmt = con.createStatement();

        // 5 执行sql
        ResultSet rs = stmt.executeQuery(sql); // 返回的是受影响的行数
        //创建集合
        List<Score> list = new ArrayList<Score>();
        //6 c处理结果
        //System.out.println(count);
        while(rs.next()){
            Score score = new Score();
            score.setId(rs.getInt("id"));
            score.setName(rs.getString("name"));
            score.setMath(rs.getInt("math"));
            score.setEnglish(rs.getInt("english"));
            score.setChniese(rs.getInt("chinese"));
            list.add(score);

        }
        System.out.println(list);
        //释放资源
        stmt.close();
        con.close();
        //数据库链接池
        /*
        * 实现表展结构 DataSource
        * 常见的 DBCP C3P0 Druid
        * */






    }
}
