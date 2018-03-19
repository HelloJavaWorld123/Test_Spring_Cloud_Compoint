/**
 * ======================
 * Created By User: RXK
 * Date: 2018/3/14  17:34
 * Version: V1.0
 * Description:
 * ======================
 */
public class MethodTest {

    public static void main(String[] agrs){
        People a = new People();
        a.age = 2 ;
        People b ;
        b = a ;

        System.out.println(++a.age);
        System.out.println(++b.age);
        System.out.println((++a.age)+(++b.age));
    }


}

class People{
    Integer age ;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
