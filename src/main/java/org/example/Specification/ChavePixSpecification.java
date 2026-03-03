package org.example.Specification;

import jakarta.persistence.criteria.Predicate;
import org.example.Entity.ChavePix;
import org.example.Entity.TipoChave;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ChavePixSpecification {

    public static Specification<ChavePix> comFiltros(
            UUID id, TipoChave tipoChave, Integer numeroAgencia, Integer numeroConta,
            String nomeCorrentista, LocalDate dataInclusao, LocalDate dataInativacao) {

        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != null) {
                predicates.add(builder.equal(root.get("id"), id));
                return builder.and(predicates.toArray(new Predicate[0]));
            }

            if (tipoChave != null) {
                predicates.add(builder.equal(root.get("tipoChave"), tipoChave));
            }
            if (numeroAgencia != null) {
                predicates.add(builder.equal(root.get("numeroAgencia"), numeroAgencia));
            }
            if (numeroConta != null) {
                predicates.add(builder.equal(root.get("numeroConta"), numeroConta));
            }
            if (nomeCorrentista != null) {
                predicates.add(builder.like(builder.lower(root.get("nomeCorrentista")), "%" + nomeCorrentista.toLowerCase() + "%"));
            }

            if (dataInclusao != null) {
                LocalDateTime inicioDoDia = dataInclusao.atStartOfDay();
                LocalDateTime fimDoDia = dataInclusao.atTime(LocalTime.MAX);
                predicates.add(builder.between(root.get("dataHoraInclusao"), inicioDoDia, fimDoDia));
            }
            if (dataInativacao != null) {
                LocalDateTime inicioDoDia = dataInativacao.atStartOfDay();
                LocalDateTime fimDoDia = dataInativacao.atTime(LocalTime.MAX);
                predicates.add(builder.between(root.get("dataHoraInativacao"), inicioDoDia, fimDoDia));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
