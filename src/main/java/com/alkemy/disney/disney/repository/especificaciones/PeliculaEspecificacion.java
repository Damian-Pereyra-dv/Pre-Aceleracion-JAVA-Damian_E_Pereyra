package com.alkemy.disney.disney.repository.especificaciones;

import com.alkemy.disney.disney.entity.GeneroEntity;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Component
public class PeliculaEspecificacion {

    public Specification<PeliculaEntity> getByFilters(String nombre, Set<Long> genero, String orden) {
        // Lambda expression
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasLength(nombre)) {
                predicates.add((Predicate) criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nombre")),
                        "%" + nombre.toLowerCase() + "%"));
            }
            if (!CollectionUtils.isEmpty(genero)) {
                Join<GeneroEntity, PeliculaEntity> join = root.join("genero", JoinType.INNER);
                    Expression<String> generoID = join.get("id");
                predicates.add((Predicate) generoID.in(genero));
            }
            // Remove duplicates
            query.distinct(true);
            // Order resolver
            String orderByField = "fechaCreacion";
            // Ternary operator
            query.orderBy(
                    orden.equalsIgnoreCase("asc") ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField)));
            return criteriaBuilder.and(predicates.toArray(new javax.persistence.criteria.Predicate[0]));
        };
    }
}
