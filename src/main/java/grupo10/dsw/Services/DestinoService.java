package grupo10.dsw.Services;

import grupo10.dsw.Entities.Destino;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DestinoService {

    private final Map<UUID, Destino> repository = new HashMap<>();


    public Destino create(Destino destino) {

        repository.put( destino.getId(),destino);
        return destino;
    }
    public List<Destino> findAll() {
        return new ArrayList<>(repository.values());
    }
    public List<Destino>  find(String nome, String cidade, String estado, String pais) {
            List<Destino> respostas = repository.values().stream().filter(destino ->
               (pais == null || pais.isEmpty() || destino.getPais().startsWith(pais)) &&
               (nome == null || nome.isEmpty() || destino.getNome().startsWith(nome)) &&
               (cidade == null || cidade.isEmpty() || destino.getCidade().startsWith(cidade)) &&
               (estado == null || estado.isEmpty() || destino.getEstado().startsWith(estado))
            ).toList();

        return respostas;
    }
    public Destino avaliar(Integer nota, UUID idDestino){

        if(nota > 5 || nota < 0){
            return null;
        }
        Destino destino = repository.get(idDestino);
        if(destino == null){
            return null;
        }
        destino.setAvaliacoes(nota);
        repository.put(idDestino,destino);
        return destino;
    }

    public void delete(UUID idDestino){
        Destino destino = repository.get(idDestino);
        if(destino != null){
            repository.remove(idDestino);
        }
        else{
            throw new RuntimeException("Destino não encontrado");
        }
    }


}
