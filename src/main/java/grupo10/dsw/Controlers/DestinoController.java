package grupo10.dsw.Controlers;

import grupo10.dsw.Entities.Destino;
import grupo10.dsw.Services.DestinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/destinos")
@RequiredArgsConstructor
public class DestinoController {

    private final DestinoService destinoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Destino destino){
        Destino res = destinoService.create(destino);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getDestinos(){
        return new ResponseEntity<>(destinoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getDestinosByFilter(@RequestParam(required = false) String nome, @RequestParam(required = false) String cidade, @RequestParam(required = false) String estado, @RequestParam(required = false) String pais){
        return new ResponseEntity<>(destinoService.find(nome, cidade, estado, pais), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getDestinoById(@PathVariable UUID id){
        try {
            Destino destino = destinoService.findById(id);
            return new ResponseEntity<>(destino, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/{id}")
    public ResponseEntity<?> avaliar(@RequestBody Integer nota, @PathVariable UUID id){

        return new ResponseEntity<>(destinoService.avaliar(nota, id), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        destinoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
