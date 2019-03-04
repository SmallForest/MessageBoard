# 使用servlet service dao实现的留言板功能
- 实现公共留言板查看
- 登录注册
- 个人信息维护
- 个人留言列表
- 个人留言编辑&删除操作
- 权限验证
- jdbc
- c3p0连接池
# c3p0连接池
# jdbc数据库操作
# Kaptcha 验证码
# 权限过滤器
# 字符过滤器
## 单例的定义
```
public class MessageService {
    private MessageDAO messageDao;

    /**
     * 通过私有构造方法生成dao
     * 私有构造方法
     */
    private MessageService() {
        messageDao = new MessageDAO();
    }
    //声明私有属性
    private static MessageService single=null;

    /**
     * 声明获取对象方法
     * 双重检查
     * @return
     */
    public static MessageService getInstance(){
        if (null == single){
            synchronized (MessageService.class){
                if (null == single){
                    single = new MessageService();
                }
            }
        }
        return single;
    }
}
```
## 单例的使用
```
MessageService ms = MessageService.getInstance();
```

