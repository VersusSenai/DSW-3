package grupo10.dsw.Repositories;

import grupo10.dsw.Entities.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface DestinoRepository extends JpaRepository<Destino, UUID> {

    @Query("""
    SELECT d FROM Destino d
    WHERE (:nome IS NULL OR LOWER(d.nome) LIKE LOWER(CONCAT('%', :nome, '%')))
       OR (:cidade IS NULL OR LOWER(d.cidade) LIKE LOWER(CONCAT('%', :cidade, '%')))
       OR (:estado IS NULL OR LOWER(d.estado) LIKE LOWER(CONCAT('%', :estado, '%')))
       OR (:pais IS NULL OR LOWER(d.pais) LIKE LOWER(CONCAT('%', :pais, '%')))
""")
    List<Destino> findByFiltro(
            @Param("nome") String nome,
            @Param("cidade") String cidade,
            @Param("estado") String estado,
            @Param("pais") String pais
    );}
