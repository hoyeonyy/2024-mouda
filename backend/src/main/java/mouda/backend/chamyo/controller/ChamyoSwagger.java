package mouda.backend.chamyo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import mouda.backend.chamyo.dto.request.ChamyoCancelRequest;
import mouda.backend.chamyo.dto.request.MoimChamyoRequest;
import mouda.backend.chamyo.dto.response.ChamyoFindAllResponses;
import mouda.backend.chamyo.dto.response.MoimRoleFindResponse;
import mouda.backend.common.RestResponse;
import mouda.backend.config.argumentresolver.LoginMember;
import mouda.backend.member.domain.Member;

public interface ChamyoSwagger {

	@Operation(summary = "모임 참여 여부 조회", description = "현재 로그인된 회원의 모임 참여 여부를 조회합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "모임 참여 여부 조회 성공")
	})
	ResponseEntity<RestResponse<MoimRoleFindResponse>> findMoimRoleByMember(@RequestParam Long moimId,
		@LoginMember Member member);

	@Operation(summary = "모든 모임 참여자 조회", description = "모임에 참여한 모든 회원을 조회합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "모든 모임 참여자 조회 성공")
	})
	ResponseEntity<RestResponse<ChamyoFindAllResponses>> findAllChamyoByMoim(@RequestParam Long moimId);

	@Operation(summary = "모임 참여", description = "모임에 참여합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "모임 참여 성공")
	})
	ResponseEntity<Void> chamyoMoim(@Valid @RequestBody MoimChamyoRequest request, @LoginMember Member member);

	@Operation(summary = "모임 참여 취소", description = "모임 참여를 취소합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "모임 참여 취소 성공")
	})
	ResponseEntity<Void> cancelChamyo(@Valid @RequestBody ChamyoCancelRequest request, @LoginMember Member member);
}
