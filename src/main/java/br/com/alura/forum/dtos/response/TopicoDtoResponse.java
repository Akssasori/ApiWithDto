package br.com.alura.forum.dtos.response;

import br.com.alura.forum.models.Curso;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repositories.CursoRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicoDtoResponse {

    @NotEmpty
    @NotEmpty
    @Length(min = 5)
    private String titulo;
    @NotEmpty
    @NotEmpty
    @Length(min = 5)
    private String mensagem;
    @NotEmpty
    @NotEmpty
    private String nomeCurso;

    public TopicoDtoResponse(Topico topico) {
    }


    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(nomeCurso);
        return new Topico(titulo, mensagem, curso);
    }
}

//form
