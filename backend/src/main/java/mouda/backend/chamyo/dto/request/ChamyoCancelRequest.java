package mouda.backend.chamyo.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ChamyoCancelRequest(
	@NotNull @Positive
	Long moimId
) {
}
