import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Solution {


    public static void main(String [] args)
    {

      System.out.println("Shallow Size: the Course Class");

      System.out.println(ClassLayout.parseClass(Course.class).toPrintable());
        System.out.println("\n\n");
      System.out.println("Shallow Size: the Professor Class");

      System.out.println(ClassLayout.parseClass(Professor.class).toPrintable());
        System.out.println("\n\n");
      System.out.println("Shallow Size: an Instance");
      String ds = "Data Structures";
      Course course = new Course(ds);
      System.out.println("The shallow size of 'course; is: " + VM.current().sizeOf(course));
        System.out.println("The shallow size of 'ds' is: " + VM.current().sizeOf(ds));
      System.out.println("\n\n");

        System.out.println("The deep size of the Course instance is equal to " +
                           "the shallow size of the Course instance itself plus the deep size of " +
                           "that particular String instance.");
        System.out.println("Deep size of the String instance ds");
        System.out.println(ClassLayout.parseInstance(ds).toPrintable());
        System.out.println("\n\n");
        System.out.println("The shallow size of this String instance is 24 bytes, which include the 4 bytes of cached hash code, 4 bytes " +
                           "of byte[] reference, and other typical object overhead.\n" +
                           "\n" +
                           "To see the actual size of the byte[], we can parse its class layout, too:");

        System.out.println(ClassLayout.parseInstance(ds.getBytes(StandardCharsets.UTF_8)).toPrintable());
        System.out.println("\n\n");
        System.out.println("Object Graph Layout");
        System.out.println(GraphLayout.parseInstance(course).toFootprint());
        System.out.println("\n\n");
        String name = "Test";
        boolean tenured = true;
        List<Course> courseList = new ArrayList<>();
        courseList.add(course);
        int level = 1;
        LocalDate birthday = LocalDate.now();
        double lastEvaluation = 56.6d;
        Professor p = new Professor(name, tenured, courseList, level, birthday, lastEvaluation);
        System.out.println(GraphLayout.parseInstance(p).toFootprint());

    }
}

