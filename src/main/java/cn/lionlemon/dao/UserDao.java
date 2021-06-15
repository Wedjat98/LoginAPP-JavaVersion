package cn.lionlemon.dao;

import cn.lionlemon.domain.User;
import cn.lionlemon.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

//提供login方法 操作数据库中user表的类
public class UserDao {
    //声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    //登录方法 loginUser中只有用户名和密码  返回的user包括用户全部数据
    public User login(User loginUser){
        try {
            //登录逻辑
            String sql ="select * from user where username= ? and password = ?";
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }


}
