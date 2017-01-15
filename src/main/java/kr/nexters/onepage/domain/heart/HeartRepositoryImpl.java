package kr.nexters.onepage.domain.heart;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import kr.nexters.onepage.domain.page.QPage;
import kr.nexters.onepage.domain.user.QUser;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

public class HeartRepositoryImpl extends QueryDslRepositorySupport implements HeartRepositoryCustom {
	QHeart qHeart = QHeart.heart;
	QUser qUser = QUser.user;
	QPage qPage = QPage.page;

	public HeartRepositoryImpl() {
		super(Heart.class);
	}

	@Override public Heart findByPageIdAndEmail(Long pageId, String email) {
		JPQLQuery<Heart> query = from(qHeart).innerJoin(qUser).innerJoin(qPage).fetchJoin();

		BooleanBuilder whereClause = new BooleanBuilder();
		whereClause.and(qHeart.page.id.eq(pageId));
		whereClause.and(qHeart.user.email.eq(email));
		return query.where(whereClause).fetchOne();
	}
}