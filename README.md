# mall-test
#20180202注意事项：
#1、依赖关系的梳理：parent中将主要用到的主要jar包及版本定义；common中定义通用jar包和工具jar包；底层依赖更改后要重新install。
#2、一些被拦截资源：web.xml中url拦截形式为“/”表示拦截所有的url请求，包括静态资源例如css、js等。所以需要在springmvc.xml中添加资源映射标签；mapper的pom.xml文件中添加（·禁用）xml和properties（过滤·）的配置。
#3、特殊的插件（jar）：Mybatis分页插件 - 原生PageHelper，对逆向生成的代码支持不好，不能对有查询条件的查询分页。自定义一个fix版本解决此问题。
#4、树型菜单的思路：轮询方式：通过parentId查询，点一次下拉三角，查询一次，通过isParent判断叶子与否。（和前台解析相关）
#5、Mybatis-generator生成的pojo和mapper：pojo对应的是实体，在更新、新增的时候，set属性后，由mapper调用对应方法进行更新、新增(insert)；查询操作时，创建的是pojoExample对象，通过Criteria向example写入对应sql语句，由mapper进行查询。
#6、Mybatis事务（尚未理解）：在applicationContext-trans.xml中配置的切面在Service，事务通知由切面发出。在controller中的一个方法中，只有调用一个Service的方法才能成功提交事务，否则后台提示保存/更新成功，实际上并没有提交到数据库。
