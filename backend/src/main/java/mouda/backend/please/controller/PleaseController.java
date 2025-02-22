package mouda.backend.please.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mouda.backend.common.RestResponse;
import mouda.backend.config.argumentresolver.LoginDarakbangMember;
import mouda.backend.darakbangmember.domain.DarakbangMember;
import mouda.backend.please.domain.Please;
import mouda.backend.please.dto.request.PleaseCreateRequest;
import mouda.backend.please.dto.response.PleaseFindAllResponses;
import mouda.backend.please.service.PleaseService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/darakbang/{darakbangId}/please")
public class PleaseController implements PleaseSwagger {

	private final PleaseService pleaseService;

	@Override
	@PostMapping
	public ResponseEntity<RestResponse<Long>> createPlease(
		@PathVariable Long darakbangId,
		@LoginDarakbangMember DarakbangMember member,
		@Valid @RequestBody PleaseCreateRequest pleaseCreateRequest
	) {
		Please please = pleaseService.createPlease(darakbangId, member, pleaseCreateRequest);

		return ResponseEntity.ok().body(new RestResponse<>(please.getId()));
	}

	@Override
	@DeleteMapping("/{pleaseId}")
	public ResponseEntity<Void> deletePlease(
		@PathVariable Long darakbangId,
		@LoginDarakbangMember DarakbangMember member,
		@PathVariable Long pleaseId
	) {
		pleaseService.deletePlease(darakbangId, pleaseId, member);

		return ResponseEntity.ok().build();
	}

	@Override
	@GetMapping
	public ResponseEntity<RestResponse<PleaseFindAllResponses>> findAllPlease(
		@PathVariable Long darakbangId,
		@LoginDarakbangMember DarakbangMember member
	) {
		PleaseFindAllResponses responses = pleaseService.findAllPlease(darakbangId, member);

		return ResponseEntity.ok().body(new RestResponse<>(responses));
	}
}
