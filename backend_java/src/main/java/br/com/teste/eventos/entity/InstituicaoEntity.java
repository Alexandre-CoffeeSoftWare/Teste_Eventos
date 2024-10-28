package br.com.teste.eventos.entity;

import br.com.teste.eventos.constants.TipoInstituicao;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Entity
@Table(name ="instituicao")
@Getter
@Setter
@NoArgsConstructor
public class InstituicaoEntity implements Serializable {

    public InstituicaoEntity(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "tipo_instituicao", nullable = false)
    private TipoInstituicao tipoInstituicao;
}
