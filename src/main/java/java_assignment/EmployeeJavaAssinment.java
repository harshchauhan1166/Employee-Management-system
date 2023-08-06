package java_assignment;

import java.util.*;

public class EmployeeJavaAssinment {


    int empid;
    float salary;

    public void getInput() {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter the empid :: ");
        empid = in.nextInt();
        System.out.print("Enter the salary :: ");
        salary = in.nextFloat();
    }

    public void display() {

        System.out.println("Employee id = " + empid);
        System.out.println("Employee salary = " + salary);
    }

    public static void main(String a[]) {

        EmployeeJavaAssinment e[] = new EmployeeJavaAssinment[5];

        float sal[] = new float[5];

        for (int i = 0; i < 5; i++) {

            e[i] = new EmployeeJavaAssinment();

            e[i].getInput();

            sal[i] = e[i].salary;

        }


        float max = sal[0];

        float arr[] = new float[5];

        int j = 0;

        for (int i = 0; i < 5; i++) {

            if (max < sal[i]) {

                max = sal[i];

                arr[j] = max;

                j = i;

            }

        }

        System.out.println("***********Employee Having Maximum Salary ************");

        e[j].display();

    }


}

