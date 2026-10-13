package br.edu.ifma.imobiliaria.exception;

public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String recurso, Integer id) {
        super(recurso + " não encontrado(a) com id " + id);
    }
}