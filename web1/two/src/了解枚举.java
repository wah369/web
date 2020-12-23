/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2020-12-23
 * Time: 11:14
 */
public class 了解枚举 {
    enum Gender {
        FEMALE,MALE;
    }

    public static void main(String[] args) {
        Gender[] values = Gender.values();
        for (Gender gender : values) {
            System.out.println(gender);
        }
    }
}
