package mouda.backend.moim.dto.response;

import static java.time.format.DateTimeFormatter.*;

import java.util.List;

import lombok.Builder;
import mouda.backend.comment.dto.response.CommentResponse;
import mouda.backend.moim.domain.Moim;

@Builder
public record MoimDetailsFindResponse(
	String title,
	String date,
	String time,
	String place,
	int currentPeople,
	int maxPeople,
	String authorNickname,
	String description,
	String status,
	List<CommentResponse> comments
) {

	public static MoimDetailsFindResponse toResponse(Moim moim, int currentPeople, List<CommentResponse> comments) {
		String time = moim.getTime() == null ? "" : moim.getTime().format(ofPattern("HH:mm"));
		String date = moim.getDate() == null ? "" : moim.getDate().format(ISO_LOCAL_DATE);
		String place = moim.getPlace() == null ? "" : moim.getPlace();
		return MoimDetailsFindResponse.builder()
			.title(moim.getTitle())
			.date(date)
			.time(time)
			.place(place)
			.currentPeople(currentPeople)
			.maxPeople(moim.getMaxPeople())
			.description(moim.getDescription())
			.status(moim.getMoimStatus().name())
			.comments(comments)
			.build();
	}
}
