package kr.nexters.onepage.domain.page;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import kr.nexters.onepage.domain.heart.QHeart;
import kr.nexters.onepage.domain.location.QLocation;
import kr.nexters.onepage.domain.user.QUser;
import kr.nexters.onepage.domain.util.LocalDateRange;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PageRepositoryImpl extends QueryDslRepositorySupport implements PageRepositoryCustom {
	private QPage qPage = QPage.page;
	private QLocation qLocation = QLocation.location;
	private QUser qUser = QUser.user;
	private QHeart qHeart = QHeart.heart;

	public PageRepositoryImpl() {
		super(Page.class);
	}

	@Override
	public List<Page> findByLocationIdAndPageable(Long locationId, int offset, int perSize) {
		return from(qPage).innerJoin(qPage.location, qLocation)
				.where(qPage.location.id.eq(locationId))
				.orderBy(qPage.id.desc())
				.offset(offset)
				.limit(perSize).fetch();
	}

	@Override
	public List<Page> findByEmailAndPageable(String email, int offset, int perSize) {
		return from(qPage).innerJoin(qPage.user, qUser)
				.where(qPage.user.email.eq(email))
				.orderBy(qPage.id.desc())
				.offset(offset)
				.limit(perSize).fetch();
	}

	@Override
	public List<Page> findByHeartAndPageable(String email, int offset, int perSize) {
		JPQLQuery<Page> query = from(qPage);
		JPQLQuery<Long> subQuery = JPAExpressions.select(qHeart.page.id)
			.from(qHeart)
			.innerJoin(qHeart.user, qUser)
			.innerJoin(qHeart.page, qPage)
			.where(qHeart.user.email.eq(email));
		return query.where(qPage.id.in(subQuery))
			.orderBy(qPage.id.desc())
			.offset(offset)
			.limit(perSize)
			.fetch();
	}

	@Override public long countByLocationIdAndRange(Long locationId, LocalDateRange range) {
		JPQLQuery<Page> query = from(qPage).innerJoin(qPage.location, qLocation);
		BooleanBuilder whereClause = new BooleanBuilder();
		whereClause.and(qPage.location.id.eq(locationId));
		whereClause.and(qPage.createdAt.between(range.getStartDateTime(), range.getEndDateTime()));
		return query.where(whereClause).fetchCount();
	}

	@Override
	public int countByEmailAndHeart(String email){
		JPQLQuery<Page> query = from(qPage);
		JPQLQuery<Long> subQuery = JPAExpressions.select(qHeart.page.id)
			.from(qHeart)
			.innerJoin(qHeart.user, qUser)
			.innerJoin(qHeart.page, qPage)
			.where(qHeart.user.email.eq(email));
		return (int) query.where(qPage.id.in(subQuery)).fetchCount();
	}
}
