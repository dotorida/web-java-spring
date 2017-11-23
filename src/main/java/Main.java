import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class Main {
       
    public static void main(String[] args) {
        System.out.println("Ola");
        BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin"));
    }
}

/**
 * cd ${current.project.path}
javac -classpath ${project.java.classpath} -sourcepath ${project.java.sourcepath} -d ${project.java.output.dir} src/Main.java
java -classpath ${project.java.classpath}${project.java.output.dir} Main
 */
