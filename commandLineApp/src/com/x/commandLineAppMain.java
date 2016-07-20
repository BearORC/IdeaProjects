package com.x;
import java.sql.*;

public class commandLineAppMain {
    public static void main(String args[]) {
        // 驱动程序名
        String driver = "com.mysql.jdbc.Driver";

        // URL指向要访问的数据库名scutcs
        String url = "jdbc:mysql://45.4.8.9:3306/KDMAAA";

        // MySQL配置时的用户名
        String user = "root";

        // MySQL配置时的密码
        String password = "kdc";
        try {
            // 加载驱动程序
            Class.forName(driver);

            // 连续数据库
            Connection conn = DriverManager.getConnection(url, user, password);

            if (!conn.isClosed())
                System.out.println("Succeeded connecting to the Database!");

            // statement用来执行SQL语句
            Statement statement = conn.createStatement();

            // 要执行的SQL语句
            String sql = "select * from tblDevice";

            // 结果集
            ResultSet rs = statement.executeQuery(sql);

            String devName;

            while (rs.next()) {

                // 选择sname这列数据
                devName = rs.getString("devName");

                // 首先使用ISO-8859-1字符集将name解码为字节序列并将结果存储新的字节数组中。
                // 然后使用GB2312字符集解码指定的字节数组
                devName = new String(devName.getBytes("utf8"), "utf8");

                // 输出结果
                System.out.println(devName + rs.getString("devId")+"@nnpa");
            }

            rs.close();
            conn.close();

        } catch (ClassNotFoundException e) {


            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();

        } catch (Exception e) {

            e.printStackTrace();


        }
    }
}
