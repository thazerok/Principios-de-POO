class EstudianteYaInscritoException extends Exception {
    public EstudianteYaInscritoException(String mensaje) {
        super(mensaje);
    }
}

class EstudianteNoInscritoEnCursoException extends Exception {
    public EstudianteNoInscritoEnCursoException(String mensaje) {
        super(mensaje);
    }
}