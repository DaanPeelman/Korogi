package com.korogi.rest.specification;

import static com.korogi.core.domain.Anime_.NAME_ENGLISH;
import static com.korogi.core.domain.Anime_.NAME_ROMANIZED;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import com.korogi.core.domain.Anime;
import net.kaczmarzyk.spring.data.jpa.domain.PathSpecification;
import net.kaczmarzyk.spring.data.jpa.utils.QueryContext;

public class SimilarAnimeName extends PathSpecification<Anime> {
    private static final String LIKE_SIMILARITY = "LIKE_SIMILARITY";
    private static final String SIMILARITY = "SIMILARITY";

    private final String expectedValue;

    public SimilarAnimeName(
        QueryContext queryContext,
        String path,
        String[] httpParamValues
    ) {
        super(queryContext, path);
        this.expectedValue = httpParamValues[0];
    }

    @Override
    public Predicate toPredicate(
        Root<Anime> anime,
        CriteriaQuery<?> query,
        CriteriaBuilder criteriaBuilder
    ) {
        query.orderBy(
            criteriaBuilder.desc(
                criteriaBuilder.sum(
                    similarityExpression(criteriaBuilder, anime, NAME_ENGLISH),
                    similarityExpression(criteriaBuilder, anime, NAME_ROMANIZED)
                )
            )
        );

        return criteriaBuilder.or(
            criteriaBuilder.isTrue(likeSimilarityExpression(criteriaBuilder, anime, NAME_ENGLISH)),
            criteriaBuilder.isTrue(likeSimilarityExpression(criteriaBuilder, anime, NAME_ROMANIZED))
        );
    }

    private Expression<BigDecimal> similarityExpression(
        CriteriaBuilder criteriaBuilder,
        Root<Anime> anime,
        String path
    ) {
        return criteriaBuilder.function(
            SIMILARITY,
            BigDecimal.class,
            anime.get(path),
            criteriaBuilder.literal(this.expectedValue)
        );
    }

    private Expression<Boolean> likeSimilarityExpression(
        CriteriaBuilder criteriaBuilder,
        Root<Anime> anime,
        String path
    ) {
        return criteriaBuilder.function(
            LIKE_SIMILARITY,
            Boolean.class,
            anime.get(path),
            criteriaBuilder.literal(this.expectedValue)
        );
    }

}
