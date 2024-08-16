import BackArrowButton from '@_components/BackArrowButton/BackArrowButton';
import FunnelButton from '@_components/Funnel/FunnelButton/FunnelButton';
import FunnelStepIndicator from '@_components/Funnel/FunnelStepIndicator/FunnelStepIndicator';
import ROUTES from '@_constants/routes';
import FunnelLayout from '@_layouts/FunnelLayout/FunnelLayout';
import { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import TitleStep from './Steps/TitleStep';
import OfflineOrOnlineStep from './Steps/OfflineOrOnlineStep';
import PlaceStep from './Steps/PlaceStep';

export type MoimCreationStep =
  | '이름입력'
  | '오프라인/온라인선택'
  | '장소선택'
  | '날짜/시간설정'
  | '최대인원설정'
  | '설명입력';

const steps: MoimCreationStep[] = [
  '이름입력',
  '오프라인/온라인선택',
  '장소선택',
  '날짜/시간설정',
  '최대인원설정',
  '설명입력',
];

export default function MoimCreationPage() {
  const navigate = useNavigate();
  const location = useLocation();

  const currentStep: MoimCreationStep = location.state?.step || steps[0];

  const [moimInfo, setMoimInfo] = useState({
    title: '',
    offlineOrOnline: '',
    place: '',
    date: '',
    time: '',
    maxPeople: 0,
    description: '',
  });

  const currentComponents: {
    main: JSX.Element;
    footer: JSX.Element;
  } = { main: <></>, footer: <></> };

  if (currentStep === '이름입력') {
    currentComponents.main = (
      <TitleStep
        title={moimInfo.title}
        onTitleChange={(title) => setMoimInfo((prev) => ({ ...prev, title }))}
      />
    );
    currentComponents.footer = (
      <FunnelButton
        disabled={moimInfo.title === ''}
        onClick={() => {
          navigate(ROUTES.addMoim, {
            state: { step: '오프라인/온라인선택' },
          });
        }}
      >
        {moimInfo.title === '' ? '모임 이름을 입력해주세요' : '다음으로'}
      </FunnelButton>
    );
  } else if (currentStep === '오프라인/온라인선택') {
    currentComponents.main = (
      <OfflineOrOnlineStep
        offlineOrOnline={moimInfo.offlineOrOnline}
        onOfflineOrOnlineChange={(offlineOrOnline) =>
          setMoimInfo((prev) => ({
            ...prev,
            offlineOrOnline: offlineOrOnline,
          }))
        }
      />
    );
    currentComponents.footer = (
      <FunnelButton
        disabled={moimInfo.title === ''}
        onClick={() => {
          navigate(ROUTES.addMoim, {
            state: {
              step: moimInfo.offlineOrOnline ? '장소선택' : '날짜/시간설정',
            },
          });
        }}
      >
        {moimInfo.offlineOrOnline === ''
          ? '스킵하고 채팅에서 정할게요!'
          : '다음으로'}
      </FunnelButton>
    );
  } else if (currentStep === '장소선택') {
    currentComponents.main = (
      <PlaceStep
        offlineOrOnline={moimInfo.offlineOrOnline}
        place={moimInfo.place}
        onPlaceChange={(place) => setMoimInfo((prev) => ({ ...prev, place }))}
      />
    );
    currentComponents.footer = (
      <FunnelButton
        disabled={moimInfo.title === ''}
        onClick={() => {
          navigate(ROUTES.addMoim, {
            state: { step: '날짜/시간설정' },
          });
        }}
      />
    );
  }

  return (
    <FunnelLayout>
      <FunnelLayout.Header>
        <FunnelLayout.Header.Left>
          <BackArrowButton onClick={() => navigate(-1)} />
        </FunnelLayout.Header.Left>
        <FunnelLayout.Header.Center>모임 만들기</FunnelLayout.Header.Center>
      </FunnelLayout.Header>

      <FunnelStepIndicator totalSteps={steps} currentStep={currentStep} />

      <FunnelLayout.Main>{currentComponents?.main}</FunnelLayout.Main>

      <FunnelLayout.Footer>{currentComponents?.footer}</FunnelLayout.Footer>
    </FunnelLayout>
  );
}
