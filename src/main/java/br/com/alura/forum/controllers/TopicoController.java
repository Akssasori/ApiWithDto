package br.com.alura.forum.controllers;

import br.com.alura.forum.dtos.TopicoDto;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repositories.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @GetMapping("/topicos")
    public List<TopicoDto> lista(){
        List<Topico> topicos = topicoRepository.findAll();
        return TopicoDto.converter(topicos);
    }
}
