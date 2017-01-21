package kr.nexters.onepage.domain.page;

import com.google.common.collect.Lists;
import com.querydsl.jpa.JPQLQuery;
import kr.nexters.onepage.domain.heart.QHeart;
import kr.nexters.onepage.domain.location.QLocation;
import kr.nexters.onepage.domain.user.QUser;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.List;

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
		JPQLQuery<Page> query = from(qPage).innerJoin(qPage.user, qUser);
		/*
		select *
		from onepage.page a
		where a.pageId in
		( select pageId
		  from onepage.heart a join onepage.page b
		  on a.pageId = b.pageId
		  join onepage.user c
		  on a.userId = c.userId
		  where c.email = 'myemail' );
		 */
		return Lists.newArrayList();
	}


}
