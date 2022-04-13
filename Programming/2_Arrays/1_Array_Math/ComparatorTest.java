import java.util.*;

public class ComparatorTest {

    public static void main(String[] args) {

        List<Student> students= new ArrayList<Student>();

        Student stud1 = new Student(1, "ABC", 99);
        Student stud2 = new Student(2, "Random", 77);
        Student stud3 = new Student(3, "bsjkfbkds", 89);

        students.add(stud1);
        students.add(stud2);
        students.add(stud3);

        Collections.sort(students);

        for(Student s: students) {
            System.out.println(s);
        }

    }
}

class Student implements Comparable<Student>{

    private int rollNo;
    private String name;
    private int weight;

    Student(int rollNo, String name, int weight) {
        this.rollNo = rollNo;
        this.name = name;
        this.weight = weight;
    }

    @Override
    public int compareTo(Student s) {
        return this.weight - s.weight;
    }

    @Override
    public String toString() {

        return rollNo + " " + name + " " + weight;
    }
}