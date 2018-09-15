package demo;

import Entity.Student;
import jdk.nashorn.internal.scripts.JO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;

import java.util.List;

import static javax.swing.JOptionPane.showInputDialog;

public class CRUDoperator
{
    public static void main(String[] args)
    {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        int answer=0;
        try{

            for(;;){
        JOptionPane.showMessageDialog(null,"Welcome in Typicall CRUD-operation panel");
        answer = Integer.parseInt(JOptionPane.showInputDialog(null,"Chose what you want? \n 1 Create new Student \n 2 Update email on student \n 3 Read information About Student \n 4 Delete student \n 5 Exit Program" ));

            if (answer == 1) {
                try {
                    Session session = factory.getCurrentSession();
                    session.beginTransaction();
                    String name = JOptionPane.showInputDialog("Give me name of this student");
                    String lastName = JOptionPane.showInputDialog("Give me last Name of this student");
                    String email = JOptionPane.showInputDialog("Give me email of this student");
                    Student newStudent = new Student(name, lastName, email);
                    session.save(newStudent);
                    session.getTransaction().commit();
                }
                finally {
                    System.out.println("Zakończono operację");
            }}

            else if (answer == 2) {
                try {
                    Session session = factory.getCurrentSession();
                    session.beginTransaction();
                    JOptionPane.showMessageDialog(null, "Give me name and lastName of student which one you want to change mail");
                    String typeName = JOptionPane.showInputDialog("Give me name of this student");
                    String typeLastName = JOptionPane.showInputDialog("Give me last Name of this student");
                    String newMail = JOptionPane.showInputDialog("Give me the new mail");
                    session.createQuery("update Student set email= '" + newMail + "' where firstName='" + typeName + "' AND lastName='" + typeLastName + "'").executeUpdate();
                    session.getTransaction().commit();
                }
                finally {
                    System.out.println("Zakończono operację");
                }
            }
            else if (answer == 3) {
                try {
                    Session session = factory.getCurrentSession();
                    session.beginTransaction();
                    JOptionPane.showMessageDialog(null, "Give me name of student which you want to read");
                    String typeLastName = JOptionPane.showInputDialog("Give me last Name of this student");
                    List<Student> listofStudents = session.createQuery("from Student where lastName='" + typeLastName + "'").getResultList();
                    for (Student tempStudent : listofStudents) {
                        JOptionPane.showMessageDialog(null, tempStudent);
                    }
                    session.getTransaction().commit();
                }
                finally {
                    System.out.println("zakonczono operacje");
                }
            }

            else if (answer == 4) {
                try {
                    Session session = factory.getCurrentSession();
                    session.beginTransaction();
                    JOptionPane.showMessageDialog(null, "Give me last name of student which you want to delete");
                    String typeLastName = JOptionPane.showInputDialog("Give me last Name of this student");
                    String password = JOptionPane.showInputDialog("Give me admin password");
                    if (password.equals("hbstudent")) {
                        session.createQuery("delete Student where lastName='" + typeLastName + "'").executeUpdate();
                        JOptionPane.showMessageDialog(null, "Operacja wykonana pomyślnie");
                    } else {

                       JOptionPane.showInputDialog(null, "Złe hasło ");
                    }

                    session.getTransaction().commit();
                }
                finally {
                    System.out.println("Zakończono operację");
                }
            }
            if(answer==5)
            {
                System.exit(1);
            }


        }}
        finally {
            factory.close();
        }




    }
}
