package com.alkemy.disney.disney.repository.spec;


import com.alkemy.disney.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
@Component
public class CharacterSpecification {
    public Specification<CharacterEntity> getByFilters(CharacterFiltersDTO filtersDTO) {

        //By name
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasLength(filtersDTO.getName())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + filtersDTO.getName().toLowerCase() + "%"));
            }

            //By age
            if (filtersDTO.getAge() != null) {
                predicates.add(criteriaBuilder.equal(root.get("age"), filtersDTO.getAge()));
            }

            //By movies

            if (!CollectionUtils.isEmpty(filtersDTO.getMovies())) {
                Join<CharacterEntity, MovieEntity> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filtersDTO.getMovies()));
            }

            query.distinct(true);

            //Order
            query.orderBy(criteriaBuilder.asc(root.get("name")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}




