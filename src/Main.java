import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Crear una instancia del gestor académico
        GestorAcademico gestor = new GestorAcademico();

        // Agregar algunos estudiantes por defecto
        Estudiante estudiante1 = new Estudiante(1, "Gadman", "Alvarez", "2004-20-6", "matriculado");
        Estudiante estudiante2 = new Estudiante(2, "Iker", "Garrido", "2006-01-17", "matriculado");

        // Matricular estudiantes
        gestor.matricularEstudiante(estudiante1);
        gestor.matricularEstudiante(estudiante2);

        // Agregar algunos cursos por defecto
        Curso curso1 = new Curso(1, "Programación Java", "Curso introductorio a Java", 4, "1.0");
        Curso curso2 = new Curso(2, "Base de Datos", "Introducción a las bases de datos", 3, "1.0");


        // Agregar cursos
        gestor.agregarCurso(curso1);
        gestor.agregarCurso(curso2);

        // Imprimir estudiantes
        gestor.imprimirEstudiantes();

        // Imprimir cursos
        gestor.imprimirCursos();

        System.out.println("Agregar Estudiante");
        System.out.print("Nombre: ");
        String new_estudiante_nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String new_estudiante_apellido = sc.nextLine();
        System.out.print("Fecha de Nacimiento: ");
        String new_estudiante_FN = sc.nextLine();
        Estudiante estudiante3 = new Estudiante(3, new_estudiante_nombre, new_estudiante_apellido, new_estudiante_FN, "matriculado");
        gestor.matricularEstudiante(estudiante3);

        System.out.println("Estudiante "+new_estudiante_nombre+" Agregado correctamente");

        int decision = 0;
        try {
            while (decision != 1 && decision != 2) {
                System.out.println("Agregar a curso");
                System.out.println("1. 'Base de Datos'");
                System.out.println("2. 'Programación Java'");

                decision = sc.nextInt();
                sc.nextLine();

                if (decision == 1) {
                    // Inscribir estudiantes en cursos
                    try {
                        gestor.inscribirEstudianteCurso(estudiante3, curso2.getId());
                    } catch (EstudianteYaInscritoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                } else if (decision == 2) {
                    try {
                        gestor.inscribirEstudianteCurso(estudiante3, curso1.getId());
                    } catch (EstudianteYaInscritoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                } else {
                    System.out.println("Opción no válida");
                }
            }
        } catch (Exception e) {
            System.out.println("Opción no válida");
        }


        // Inscribir estudiantes en cursos
        try {
            gestor.inscribirEstudianteCurso(estudiante1, curso1.getId());
            gestor.inscribirEstudianteCurso(estudiante1, curso2.getId());
            gestor.inscribirEstudianteCurso(estudiante2, curso1.getId());
        } catch (EstudianteYaInscritoException e) {
            System.out.println(e.getMessage());
        }

        // Imprimir estudiantes
        gestor.imprimirEstudiantes();

        // Imprimir cursos
        gestor.imprimirCursos();



        // Imprimir estudiantes por curso
        gestor.imprimirEstudiantesPorCurso();

        // Desinscribir un estudiante de un curso (ejemplo)
        try {
            gestor.desinscribirEstudianteCurso(estudiante1.id, curso1.getId());
        } catch (EstudianteNoInscritoEnCursoException e) {
            System.out.println(e.getMessage());
        }
    }
}