package br.com.alura.forum.dtos.request;

import br.com.alura.forum.models.Topico;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicoDtoRequest {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

    public TopicoDtoRequest(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
    }


    public static List<TopicoDtoRequest> converter(List<Topico> topicos) {
        return topicos.stream().map(TopicoDtoRequest::new).collect(Collectors.toList());
    }
}
