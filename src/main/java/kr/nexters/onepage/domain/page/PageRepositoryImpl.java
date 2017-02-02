package kr.nexters.onepage.domain.page;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

import kr.nexters.onepage.domain.heart.QHeart;
import kr.nexters.onepage.domain.location.QLocation;
import kr.nexters.onepage.domain.user.QUser;
import kr.nexters.onepage.domain.util.LocalDateRange;

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
		return from(qPage).innerJoin(qPage.location, qLocation).where(qPage.location.id.eq(locationId))
				.orderBy(qPage.id.asc()).offset(offset).limit(perSize).fetch();
	}

	@Override
	public List<Page> findByEmailAndPageable(String email, int offset, int perSize) {
		return from(qPage).innerJoin(qPage.user, qUser).where(qPage.user.email.eq(email)).orderBy(qPage.id.asc())
				.offset(offset).limit(perSize).fetch();
	}

	@Override
	public List<Page> findByHeartAndPageable(String email, int offset, int perSize) {
		JPQLQuery<Page> query = from(qPage).leftJoin(qPage,qHeart.page).leftJoin(qPage.user,qUser).fetchJoin();

		BooleanBuilder whereClause = new BooleanBuilder();
		whereClause.and(qPage.user.email.eq(email));
		whereClause.and(qPage.id.eq(qHeart.user.id));
		return (List<Page>) query.where(whereClause).offset(offset).limit(perSize).fetchAll();

		/*
		 * JPQLQuery<Heart> query = from(qHeart).innerJoin(qHeart.user, qUser).innerJoin(qHeart.page, qPage).fetchJoin();

		BooleanBuilder whereClause = new BooleanBuilder();
		whereClause.and(qHeart.page.id.eq(pageId));
		whereClause.and(qHeart.user.email.eq(email));
		 *
		 */

		/*
		select *
		from onepage.page a
		where a.pageId in
		( select pageId
		  from onepage.heart a join onepage.page b on a.pageId = b.pageId
		  join onepage.user c on a.userId = c.userId
		  where c.email = 'myemail' );
		 */
	}

	@Override public long countByLocationIdAndRange(Long locationId, LocalDateRange range) {
		JPQLQuery<Page> query = from(qPage).innerJoin(qPage.location, qLocation);
		BooleanBuilder whereClause = new BooleanBuilder();
		whereClause.and(qPage.location.id.eq(locationId));
		whereClause.and(qPage.createdAt.between(range.getStartDateTime(), range.getEndDateTime()));
		return query.where(whereClause).fetchCount();
	}

	@Override
	public int countByLocationIdAndDay(Long locationId, LocalDate today) {
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
	/*
	select *
	from onepage.page a
	where a.pageId in
	( select pageId
	  from onepage.heart a join onepage.page b on a.pageId = b.pageId
	  join onepage.user c on a.userId = c.userId
	  where c.email = 'myemail' );
	 */
	/*
	 * select *
	 * from page p left join heart h
	 * on p.id = h.pageId
	 * where p.user.name=email
	 */
	@Override
	public int totalCountByEmailAndHeart(String email){
		JPQLQuery<Page> query = from(qPage).leftJoin(qPage,qHeart.page).leftJoin(qPage.user,qUser).fetchJoin();

		BooleanBuilder whereClause = new BooleanBuilder();
		whereClause.and(qPage.user.email.eq(email));
		whereClause.and(qPage.id.eq(qHeart.user.id));
		return (int) query.where(whereClause).fetchCount();
	}
}
