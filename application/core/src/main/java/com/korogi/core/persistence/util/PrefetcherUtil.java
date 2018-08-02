package com.korogi.core.persistence.util;

import static javax.persistence.criteria.JoinType.LEFT;

import java.util.Arrays;
import javax.persistence.criteria.FetchParent;

public class PrefetcherUtil {
    public static void prefetch(FetchParent<?, ?> parent, String... relationsToPrefetch) {
        Arrays.stream(relationsToPrefetch).forEach((relation) -> parent.fetch(relation, LEFT));
    }
}