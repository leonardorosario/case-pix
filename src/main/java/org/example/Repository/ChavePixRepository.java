package org.example.Repository;

import org.example.Entity.ChavePix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChavePixRepository extends JpaRepository<ChavePix, UUID>, JpaSpecificationExecutor<ChavePix> {
    boolean existsByValorChave(String valorChave);
}
