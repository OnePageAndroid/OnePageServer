package kr.nexters.onepage.domain.user;

import java.util.Objects;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;

@Component
public class UserValidator {
	public static boolean emailOverlapValidate(Supplier<User> supplier) {
		return Objects.nonNull(supplier.get());
	}
}
