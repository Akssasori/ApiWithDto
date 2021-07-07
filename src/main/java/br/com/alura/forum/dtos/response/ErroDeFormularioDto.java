package br.com.alura.forum.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErroDeFormularioDto {

    private String campo;
    private String erro;




}
