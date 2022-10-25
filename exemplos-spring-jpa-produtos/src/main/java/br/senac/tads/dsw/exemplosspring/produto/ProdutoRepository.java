package br.senac.tads.dsw.exemplosspring.produto;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    List<Produto> findByNome(String nome);
    
    List<Produto> findByNomeIgnoreCase(String nome);
    
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    
    List<Produto> findDistinctByCategorias_IdIn(List<Integer> idsCat);
    
    Page<Produto> findDistinctByCategorias_IdIn(List<Integer> idsCat, Pageable pageable);
    
    @Query(nativeQuery = true, value = "SELECT * FROM produto p "
            + "INNER JOIN produto_categoria pc ON p.id = pc.produto_id "
            + "WHERE pc.categoria_id IN (:idsCat)")
    List<Produto> findByXpto(List<Integer> idsCat);

}
