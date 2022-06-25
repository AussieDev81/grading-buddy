# Grading Buddy

This simple application has just a few simple functions to help teachers grade coding assignments by adding metadata via annotations.

For example a teacher could add the following annotations to a submitted assignment.
```java
@Grade(marksAvailable = 10, marksAwarded = 7.5)
@Feedback(comment = "Good work overall, but it could do with more formatting")
class MyClass {
    @Feedback(
            comment = "field names should begin with lower case letters",
            resources = "www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html"
    )
    private String MyName;
}
```
Once a teacher has gone through the coding assignment file adding the grade and feedback annotations, they can then utilise the `AnnotationHandler` to access annotation data, or the `ReportHandler` to generate some simple report data.

For example if the teacher wanted to get all methods that have been annotated, they can call the AnnotationHandler's static `getAnnotatedMethods` method. 
```java
public class App{
    //Getting all methods annotated with the @Feedback or @Grade annotations
    Set<Method> methods = AnnotationHandler.getAnnotatedMethods(packageName, Feedback.class, Grade.class);
}
```
*Note: `getAnnotatedClasses` and `getAnnotatedFields` work in the exact same way*

The `ReportHandler` currently has only two methods, `generateReport`  and `getTotalGrades`.

The `getTotalGrades` method will search through the entire assignment and tally up **all awarded marks** and return the sum.
So for example if the `@Grade` annotation has been used 3 times and the `marksAwarded` were `7`, `13`, and `5.5` respectively, the `getTotalGrades` method will return the value `25.5` (7 + 13 + 5.5).

The `generateReport` method will return a `Report` object consisting of the date graded, teacher, student, annotated classes, methods, and fields, and the overall grade given to the student. 
```java
public class Demo{
    
    public static void main(String[] args) {
        //Declare the teacher and student
        Teacher teacher = new Teacher("Martin","Riggs","m.riggs@cqu.edu.au");
        Student student = new Student("Jane","Citizen","j.citizen@gmail.com");
        
        //The base package of the student's assignment
        String basePackage = "org.jane.citizen";
        //Get the total marks awarded
        double totalGrade = ReportHandler.getTotalGrades(basePackage);
        
        Report report = ReportHandler.generateReport(
                teacher,
                student,
                basePackage,
                totalGrade,
                Feedback.class, Grade.class
        );
    }
}
```
As the last parameter in the `generateReport` method accepts an input of type [varargs](https://docs.oracle.com/javase/8/docs/technotes/guides/language/varargs.html), we can pass one or more annotation classes one after the other without needing to create any array or collection to pass them in.

___
Please note that this project was mainly intended as a demonstration on publishing a java project as a maven dependency to the maven repository, however it is usable nonetheless.