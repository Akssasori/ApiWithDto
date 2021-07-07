package br.com.alura.forum.controllers;

import br.com.alura.forum.dtos.request.TopicoDtoRequest;
import br.com.alura.forum.dtos.response.AtualizacaoTopicoForm;
import br.com.alura.forum.dtos.response.DetalhesDoTopicoDtoResponse;
import br.com.alura.forum.dtos.response.TopicoDtoResponse;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repositories.CursoRepository;
import br.com.alura.forum.repositories.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    /**
     *
     * @return list of topics dto
     */
    @GetMapping("/topicos1")
    public List<TopicoDtoRequest> lista(){
        List<Topico> topicos = topicoRepository.findAll();
        return TopicoDtoRequest.converter(topicos);
    }

    /**
     *
     * @return list of topics dto filter
     */
    @GetMapping("/topicos2")
    public List<TopicoDtoRequest> lista(String nomeCurso){
        if(nomeCurso == null){
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDtoRequest.converter(topicos);
        }else{
            List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
            return TopicoDtoRequest.converter(topicos);
        }

    }

    @PostMapping("/topicos")
    @Transactional
    public ResponseEntity<TopicoDtoRequest> cadastrar(@RequestBody @Valid TopicoDtoResponse topicoDtoResponse,
                                                       UriComponentsBuilder uriBuilder){
       Topico topico = topicoDtoResponse.converter(cursoRepository);
       topicoRepository.save(topico);

       URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
       return ResponseEntity.created(uri).body(new TopicoDtoRequest(topico));
    }


    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoTopicoDtoResponse> detalhar(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok(new DetalhesDoTopicoDtoResponse(topico.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDtoRequest> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()) {
            Topico topico = form.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDtoRequest(topico));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }



}
