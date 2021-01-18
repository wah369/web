package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2021-01-18
 * Time: 9:06
 */
public class Student {

    public int id;
    public String name;
    public int age;
    public String gender;

    public Student(int id,  String name,int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    //代码式写数据可视化
    public ObservableValue<String> getIdValue() {
        String s = String.valueOf(id);
        return new SimpleStringProperty(s);
    }
    public ObservableValue<String> getNameValue() {
        return new SimpleStringProperty(name);
    }
    public ObservableValue<String> getAgeValue() {
        String s = String.valueOf(age);
        return new SimpleStringProperty(s);
    }
    public ObservableValue<String> getGenderValue() {
        return new SimpleStringProperty(gender);
    }
    //fxml写数据可视化
    public String getId() {
        return String.valueOf(id);
    }
    public String getName() {
        return name;
    }
    public String getAge() {
        return String.valueOf(age);
    }
    public String getGender() {
        return gender;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
