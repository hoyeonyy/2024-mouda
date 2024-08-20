package mouda.backend.zzim.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import mouda.backend.darakbangmember.domain.DarakbangMember;
import mouda.backend.moim.domain.Moim;
import mouda.backend.moim.repository.MoimRepository;
import mouda.backend.zzim.domain.Zzim;
import mouda.backend.zzim.dto.response.ZzimCheckResponse;
import mouda.backend.zzim.exception.ZzimErrorMessage;
import mouda.backend.zzim.exception.ZzimException;
import mouda.backend.zzim.repository.ZzimRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ZzimService {

	private final ZzimRepository zzimRepository;
	private final MoimRepository moimRepository;

	@Transactional(readOnly = true)
	public ZzimCheckResponse checkZzimByMember(Long darakbangId, Long moimId, DarakbangMember member) {
		Moim moim = moimRepository.findById(moimId)
			.orElseThrow(() -> new ZzimException(HttpStatus.NOT_FOUND, ZzimErrorMessage.MOIN_NOT_FOUND));
		if (moim.inNotDarakbang(darakbangId)) {
			throw new ZzimException(HttpStatus.BAD_REQUEST, ZzimErrorMessage.MOIM_NOT_IN_DARAKBANG);
		}

		boolean isZzimed = zzimRepository.existsByMoimIdAndMemberId(moimId, member.getId());

		return new ZzimCheckResponse(isZzimed);
	}

	public void updateZzim(Long darakbangId, Long moimId, DarakbangMember member) {
		Moim moim = moimRepository.findById(moimId)
			.orElseThrow(() -> new ZzimException(HttpStatus.NOT_FOUND, ZzimErrorMessage.MOIN_NOT_FOUND));
		if (moim.inNotDarakbang(darakbangId)) {
			throw new ZzimException(HttpStatus.BAD_REQUEST, ZzimErrorMessage.MOIM_NOT_IN_DARAKBANG);
		}

		zzimRepository.findByMoimIdAndMemberId(moimId, member.getId())
			.ifPresentOrElse(
				zzimRepository::delete,
				() -> zzimRepository.save(Zzim.builder().moim(moim).member(member).build())
			);
	}
}
