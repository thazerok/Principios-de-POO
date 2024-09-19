import java.util.ArrayList;
import java.util.HashMap;

class GestorAcademico implements ServiciosAcademicosI {
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Curso> cursos;
    private HashMap<Integer, ArrayList<Integer>> estudiantesPorCurso;

    public GestorAcademico() {
        this.estudiantes = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.estudiantesPorCurso = new HashMap<>();
    }

    @Override
    public void matricularEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    @Override
    public void agregarCurso(Curso curso) {
        cursos.add(curso);
    }

    @Override
    public void inscribirEstudianteCurso(Estudiante estudiante, int idCurso) throws EstudianteYaInscritoException {
        // Verificamos si el estudiante está matriculado
        if (!estudiantes.contains(estudiante)) {
            throw new IllegalArgumentException("El estudiante no está matriculado.");
        }

        // Verificamos si el estudiante ya está inscrito en el curso
        if (estudiantesPorCurso.containsKey(idCurso) && estudiantesPorCurso.get(idCurso).contains(estudiante.id)) {
            throw new EstudianteYaInscritoException("El estudiante ya está inscrito en el curso.");
        }

        // Inscribimos al estudiante en el curso
        if (!estudiantesPorCurso.containsKey(idCurso)) {
            estudiantesPorCurso.put(idCurso, new ArrayList<>());
        }
        estudiantesPorCurso.get(idCurso).add(estudiante.id);
    }

    @Override
    public void desinscribirEstudianteCurso(int idEstudiante, int idCurso) throws EstudianteNoInscritoEnCursoException {
        // Verificamos si el estudiante está inscrito en el curso
        if (!estudiantesPorCurso.containsKey(idCurso) || !estudiantesPorCurso.get(idCurso).contains(idEstudiante)) {
            throw new EstudianteNoInscritoEnCursoException("El estudiante no está inscrito en el curso o el ID del curso no es válido.");
        }

        // Desinscribimos al estudiante del curso
        estudiantesPorCurso.get(idCurso).remove(Integer.valueOf(idEstudiante));
    }

    // Método para imprimir estudiantes
    public void imprimirEstudiantes() {
        System.out.println("---ESTUDIANTES---");
        for (Estudiante estudiante : estudiantes) {
            System.out.println("- " + estudiante.nombre);
        }
        System.out.println();
    }

    // Método para imprimir cursos
    public void imprimirCursos() {
        System.out.println("---CURSOS---");
        for (Curso curso : cursos) {
            System.out.println("- " + curso.getNombre());
        }
        System.out.println();
    }

    // Método para imprimir qué estudiante está inscrito en qué curso
    public void imprimirEstudiantesPorCurso() {
        for (Curso curso : cursos) {
            System.out.println("Curso " + curso.getNombre() + ":");
            ArrayList<Integer> estudiantesIds = estudiantesPorCurso.get(curso.getId());
            if (estudiantesIds != null) {
                for (Integer estudianteId : estudiantesIds) {
                    for (Estudiante estudiante : estudiantes) {
                        if (estudiante.id == estudianteId) {
                            System.out.println("- " + estudiante.getNombre());
                        }
                    }
                }
            }
            System.out.println();
        }
    }
}