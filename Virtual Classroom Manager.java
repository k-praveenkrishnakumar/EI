import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Classroom {
    private String name;
    private List<Student> students;
    private List<Assignment> assignments;

    public Classroom(String name) {
        this.name = name;
        this.students = new ArrayList<>();
        this.assignments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    @Override
    public String toString() {
        return "Classroom{name='" + name + "'}";
    }
}

class Student {
    private String id;
    private String classroomName;

    public Student(String id, String classroomName) {
        this.id = id;
        this.classroomName = classroomName;
    }

    public String getId() {
        return id;
    }

    public String getClassroomName() {
        return classroomName;
    }

    @Override
    public String toString() {
        return "Student{id='" + id + "', classroomName='" + classroomName + "'}";
    }
}

class Assignment {
    private String details;
    private String classroomName;

    public Assignment(String details, String classroomName) {
        this.details = details;
        this.classroomName = classroomName;
    }

    public String getDetails() {
        return details;
    }

    public String getClassroomName() {
        return classroomName;
    }

    @Override
    public String toString() {
        return "Assignment{details='" + details + "', classroomName='" + classroomName + "'}";
    }
}

public class VirtualClassroomManager {
    private List<Classroom> classrooms;

    public VirtualClassroomManager() {
        this.classrooms = new ArrayList<>();
    }

    public void addClassroom(String className) {
        Classroom classroom = new Classroom(className);
        classrooms.add(classroom);
        System.out.println("Classroom " + className + " has been created.");
    }

    public void addStudent(String studentId, String className) {
        Classroom classroom = getClassroomByName(className);
        if (classroom != null) {
            Student student = new Student(studentId, className);
            classroom.addStudent(student);
            System.out.println("Student " + studentId + " has been enrolled in " + className + ".");
        } else {
            System.out.println("Classroom " + className + " does not exist.");
        }
    }

    public void scheduleAssignment(String className, String details) {
        Classroom classroom = getClassroomByName(className);
        if (classroom != null) {
            Assignment assignment = new Assignment(details, className);
            classroom.addAssignment(assignment);
            System.out.println("Assignment for " + className + " has been scheduled.");
        } else {
            System.out.println("Classroom " + className + " does not exist.");
        }
    }

    public void submitAssignment(String studentId, String className, String details) {
        Classroom classroom = getClassroomByName(className);
        if (classroom != null) {
            boolean studentExists = classroom.getStudents().stream()
                                             .anyMatch(student -> student.getId().equals(studentId));
            if (studentExists) {
                System.out.println("Assignment submitted by Student " + studentId + " in " + className + ".");
            } else {
                System.out.println("Student " + studentId + " is not enrolled in " + className + ".");
            }
        } else {
            System.out.println("Classroom " + className + " does not exist.");
        }
    }

    private Classroom getClassroomByName(String className) {
        return classrooms.stream()
                         .filter(classroom -> classroom.getName().equals(className))
                         .findFirst()
                         .orElse(null);
    }

    public static void main(String[] args) {
        VirtualClassroomManager manager = new VirtualClassroomManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Virtual Classroom Manager!");

        while (true) {
            System.out.print("Enter a command: ");
            String command = scanner.nextLine();
            String[] parts = command.split(" ", 2);

            if (parts.length < 2) {
                System.out.println("Invalid command. Please provide the necessary arguments.");
                continue;
            }

            switch (parts[0]) {
                case "add_classroom":
                    manager.addClassroom(parts[1]);
                    break;
                case "add_student":
                    String[] studentDetails = parts[1].split(" ");
                    if (studentDetails.length != 2) {
                        System.out.println("Invalid command. Please provide student ID and classroom name.");
                    } else {
                        manager.addStudent(studentDetails[0], studentDetails[1]);
                    }
                    break;
                case "schedule_assignment":
                    String[] assignmentDetails = parts[1].split(" ", 2);
                    if (assignmentDetails.length != 2) {
                        System.out.println("Invalid command. Please provide classroom name and assignment details.");
                    } else {
                        manager.scheduleAssignment(assignmentDetails[0], assignmentDetails[1]);
                    }
                    break;
                case "submit_assignment":
                    String[] submissionDetails = parts[1].split(" ", 3);
                    if (submissionDetails.length != 3) {
                        System.out.println("Invalid command. Please provide student ID, classroom name, and assignment details.");
                    } else {
                        manager.submitAssignment(submissionDetails[0], submissionDetails[1], submissionDetails[2]);
                    }
                    break;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }
}
