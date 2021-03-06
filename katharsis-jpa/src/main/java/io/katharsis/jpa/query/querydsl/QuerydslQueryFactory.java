package io.katharsis.jpa.query.querydsl;

import java.lang.reflect.Type;
import java.util.List;

import io.katharsis.jpa.internal.JpaQueryFactoryBase;
import io.katharsis.jpa.internal.query.backend.querydsl.QuerydslQueryImpl;
import io.katharsis.jpa.query.JpaQueryFactory;

public class QuerydslQueryFactory extends JpaQueryFactoryBase implements JpaQueryFactory {

	private QuerydslQueryFactory() {
	}

	public static QuerydslQueryFactory newInstance() {
		return new QuerydslQueryFactory();
	}

	@Override
	public <T> QuerydslQuery<T> query(Class<T> entityClass) {
		return new QuerydslQueryImpl<>(metaLookup, em, entityClass, computedAttrs);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T> QuerydslQuery<T> query(Class<?> entityClass, String attrName, List<?> entityIds) {
		return new QuerydslQueryImpl(metaLookup, em, entityClass, computedAttrs, attrName, entityIds);
	}

	public void registerComputedAttribute(Class<?> targetClass, String attributeName, Type attributeType,
			QuerydslExpressionFactory<?> expressionFactory) {
		computedAttrs.register(targetClass, attributeName, expressionFactory, attributeType);
	}
}
