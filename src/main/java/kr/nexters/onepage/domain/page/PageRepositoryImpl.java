package kr.nexters.onepage.domain.page;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import kr.nexters.onepage.domain.heart.HeartRepository;
import kr.nexters.onepage.domain.heart.QHeart;
import kr.nexters.onepage.domain.location.QLocation;
import kr.nexters.onepage.domain.user.QUser;
import kr.nexters.onepage.domain.util.LocalDateRange;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PageRepositoryImpl extends QueryDslRepositorySupport implements PageRepositoryCustom {
	private QPage qPage = QPage.page;
	private QLocation qLocation = QLocation.location;
	private QUser qUser = QUser.user;
	private QHeart qHeart = QHeart.heart;

	@Inject
	private HeartRepository heartRepository;

	public PageRepositoryImpl() {
		super(Page.class);
	}

	@Override
	public List<Page> findByLocationIdAndPageable(Long locationId, int offset, int perSize) {
		return from(qPage).innerJoin(qPage.location, qLocation).where(qPage.location.id.eq(locationId))
				.orderBy(qPage.id.asc()).offset(offset).limit(perSize).fetch();
	}

	@Override
	public List<Page> findByEmailAndPageable(String email, int offset, int perSize) {
		return from(qPage).innerJoin(qPage.user, qUser).where(qPage.user.email.eq(email)).orderBy(qPage.id.asc())
				.offset(offset).limit(perSize).fetch();
	}

	@Override public long countByLocationIdAndRange(Long locationId, LocalDateRange range) {
		JPQLQuery<Page> query = from(qPage).innerJoin(qPage.location, qLocation);
		BooleanBuilder whereClause = new BooleanBuilder();
		whereClause.and(qPage.location.id.eq(locationId));
		whereClause.and(qPage.createdAt.between(range.getStartDateTime(), range.getEndDateTime()));
		return query.where(whereClause).fetchCount();
	}

	@Override
	public long countByLocationIdAndDay(Long locationId, LocalDate today) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Page> findCircleByEmailAndHeart(String email, int pageNumber, int perPageSize){
		JPQLQuery<Page> query = from(qPage).innerJoin(qPage,qHeart.page).innerJoin(qPage.user,qUser).fetchJoin();

		BooleanBuilder whereClause = new BooleanBuilder();
		whereClause.and(qPage.user.email.eq(email));
		whereClause.and(qPage.id.eq(qHeart.user.id));
		return (List<Page>) query.where(whereClause).offset(pageNumber).limit(perPageSize).fetchAll();
	}

	@Override
	public List<Page> findByHeartAndPageable(String email, int offset, int perSize) {
		JPQLQuery<Page> query = from(qPage);
		JPQLQuery<Long> subQuery = JPAExpressions.select(qHeart.page.id)
			.from(qHeart)
			.innerJoin(qHeart.user, qUser)
			.innerJoin(qHeart.page, qPage)
			.where(qHeart.user.email.eq(email));
		return query.where(qPage.id.in(subQuery)).offset(offset).limit(perSize).fetch();
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
