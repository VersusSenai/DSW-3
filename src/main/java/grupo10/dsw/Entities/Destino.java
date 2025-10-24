package grupo10.dsw.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor @NoArgsConstructor
public class Destino {
    public UUID id = UUID.randomUUID();
    public String nome;
    public String cidade;
    public String estado;
    public String pais;
    public List<Integer> avaliacoes = new ArrayList<>();
    public Double media = 0.0;


    public void setAvaliacoes(Integer avaliacao){
        this.avaliacoes.add(avaliacao);
        this.media = ((double) this.getAvaliacoes().stream().reduce(0, Integer::sum) / (double)this.avaliacoes.size());
    }



}
