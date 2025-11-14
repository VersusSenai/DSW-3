package grupo10.dsw.Services;

import grupo10.dsw.Entities.Destino;
import grupo10.dsw.Repositories.DestinoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DestinoService {

    private final DestinoRepository repository;
    private final DestinoRepository destinoRepository;


    public Destino create(Destino destino) {

        repository.save( destino);
        return destino;
    }
    public List<Destino> findAll() {
        return repository.findAll();
    }
    public List<Destino>  find(String nome, String cidade, String estado, String pais) {
            List<Destino> respostas = repository.findAll().stream().filter(destino ->
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
        Destino destino = this.findById(idDestino);
        destino.setAvaliacoes(nota);

        return repository.save(destino);
    }

    public void delete(UUID idDestino){
        Destino d = this.findById(idDestino);
        repository.delete(d);
    }

    public Destino findById(UUID idDestino){
        return repository.findById(idDestino).orElseThrow(() ->  new EntityNotFoundException( "Destino não encontrado" ));
    }


}
