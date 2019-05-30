import java.lang.reflect.*;
import java.lang.reflect.Method;

public class UserInfo {
    private String userName;
    private Integer age;
    public String school;

    public UserInfo() {
    }

    public UserInfo(String name, Integer age, String shool) {
        this.userName = name;
        this.age = age;
        this.school = shool;
    }

    public UserInfo(String name, Integer age) {
        this.userName = name;
        this.age = age;
    }

    public String getInfo(String n, Integer i) {
        return "success" + n + i;
    }

    public void getMyInfo(String fromAddr, String userName, String language) {
        System.out.println("我是一个来自" + fromAddr + "的名叫：" + userName + "的" + language
                + "程序员");
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

class GetMyInfo {

    public static void main(String[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InvocationTargetException {
        Class className = Class.forName("UserInfo");
        Field[] fields = className.getDeclaredFields();//得到所有的字段，包括公共，保护，默认（包）和私有变量，但不包括继承的字段。
        System.out.println("------------输出所有属性--------------------");
        for (Field filed : fields) {
            int modifiers = filed.getModifiers();//属性访问权限修饰符
            String name = filed.getName();//属性名称
            Class type = filed.getType();//属性类型
            System.out.println(Modifier.toString(modifiers) + " " + type.getSimpleName() + " " + name);
        }
        System.out.println("------------输出所有方法--------------------");
        Method[] methods = className.getDeclaredMethods();
        for (Method method : methods) {
            int methodType = method.getModifiers();//访问修饰符
            String methodName = method.getName();//方法名称
            Class methodRetrunType = method.getReturnType();//返回类型
            Class[] methodParameter = method.getParameterTypes();//方法的参数列表
            System.out.print(Modifier.toString(methodType) + " " + methodRetrunType.getSimpleName() + " " + methodName + "(");
            for (int k = 0; k < methodParameter.length; k++) {
                String parameterName = methodParameter[k].getSimpleName();
                if (k != methodParameter.length - 1) {
                    System.out.print(parameterName + " arg" + k + ",");
                } else
                    System.out.print(parameterName + " arg" + k);
            }
            System.out.println(");");
        }

        System.out.println("------------输出所有构造器--------------------");
        Constructor[] constructors = className.getConstructors();
        for (Constructor constructor : constructors) {
            String constructorName = constructor.getName();
            Class[] constructorParameter = constructor.getParameterTypes();
            System.out.print(className.getSimpleName() + " " + constructorName.substring(constructorName.lastIndexOf(".") + 1, constructorName.length()) + "(");
            for (int h = 0; h < constructorParameter.length; h++) {
                String parameterName = constructorParameter[h].getSimpleName();
                if (h != constructorParameter.length - 1)
                    System.out.print(parameterName + " arg" + h + ",");
                else
                    System.out.print(parameterName + " arg" + h);
            }
            System.out.println(");");
        }
        System.out.println("------------反射执行方法--------------------");
        String name = "getMyInfo";//定义方法名变量
        Class[] parameterTypes = new Class[3];//方法的参数个数
        //方法的参数类型
        parameterTypes[0] = String.class;
        parameterTypes[1] = String.class;
        parameterTypes[2] = String.class;

        Method me = className.getDeclaredMethod(name, parameterTypes);

        Object obj = className.newInstance();
        Object[] arg = new Object[3];
        arg[0] = "安徽";
        arg[1] = "ShiGorun";
        arg[2] = "Java";
        me.invoke(obj, arg);
    }

}