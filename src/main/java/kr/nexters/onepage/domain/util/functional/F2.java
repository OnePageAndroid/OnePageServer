package kr.nexters.onepage.domain.util.functional;

import java.util.List;

@FunctionalInterface
public interface F2<P1, P2, R> {
	R apply(P1 p1, P2 p2);
}
